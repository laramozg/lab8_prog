package server.commands;

import server.FileInput;
import server.InteractionServer;
import utility.Answer;
import utility.AnswerType;
import utility.element.*;
import utility.exceptions.*;
import utility.interaction.Command;
import utility.interaction.Input;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Класс события исполнения скрипта
 */
public class ExecuteScript extends Action {

    private boolean crush;
    private final ArrayList<String> availablePrograms = new ArrayList<>();
    private final Stack<String> openFiles = new Stack<>();
    private final ArrayList<String> message = new ArrayList<>();
    private Hashtable<String,Worker> collectionReserveCopy;
    private final ActionInvoker invoker = ActionInvoker.getInstance();
    private final ReentrantLock lock = new ReentrantLock();
    String parameter = null;
    public ExecuteScript() throws SQLException {
    }

    @Override
    public Answer execute(Command command) {
        parameter = command.getParameter();
        if (parameter == null) {
            return needParameter();
        }
        if (openFiles.size() == 0) {
            lock.lock();
            message.clear();
            crush = false;
            saveCollection();
        }
        try {
            executeLoop(command);
        } catch (IOException | EnvException | IncorrectArgumentException | IncorrectCommandException |
                IncorrectCollectionException | DatabaseElementError |
                ExecuteScriptException e) {
            message.add(openFiles.pop() + ": " + e.getMessage() + "\nНе выполнено!");
            crush = true;
        }
        if (openFiles.size() == 0) {
            rollbackEventsIfError();
            lock.unlock();
            return new Answer(AnswerType.DIALOG_STATE,
                    message.stream()
                            .filter((element) -> !element.equals(""))
                            .collect(Collectors.joining("\n")));
        }
        return notifyAboutResult("");
    }


    public void saveCollection() {
        this.collectionReserveCopy = new Hashtable<>(manager.getCollection());
    }

    public void executeLoop(Command command) throws IOException {
        openFiles.push(parameter);
        fileValidation(parameter);
        Input fileReader = getFileScriptReader(parameter);
        while (fileReader.hasNext()) {
            try {
                errorChecking();
            } catch (ExecuteScriptException e) {
                break;
            }
            try {
                Command scriptCommand = fileReader.readScriptCommand();
                scriptCommand.setSender(command.getSender());
                Answer answer = invoker.execute(scriptCommand);
                if (answer.getType().toString().equals("NEED_PARAMETER")) {
                    String newPar = fileReader.getReaders().nextLine().trim();
                    scriptCommand.setParameter(newPar);
                    answer = invoker.execute(scriptCommand);
                }
                if (answer.getType().toString().equals("NEED_ELEMENT")) {
                        Worker newElement = fileReader.readElement(null, null);
                        newElement.setCreator(command.getSender());
                        scriptCommand.setElement(newElement);
                        answer = invoker.execute(scriptCommand);
                }
                message.add(answer.getMessage());
            }catch (NullPointerException er){
                message.add("Ошибка в скрипте");
            }
        }
        openFiles.pop();
    }

    private void fileValidation(String cursorFileName) {
        boolean isFileRepeat = openFiles.stream().filter((element) -> element.equals(cursorFileName)).count() > 1;
        if (!availablePrograms.contains(cursorFileName)) {
            throw new ExecuteScriptException("Такого скрипта не существует!");
        }
        if (isFileRepeat) {
            throw new ExecuteScriptException("Обнаружено рекурсивное открытие файла!");
        }
    }

    public Input getFileScriptReader(String fileAddress) throws IOException {
        Path file = Paths.get(fileAddress);
        return new FileInput(new Scanner(file));
    }

    private void errorChecking() {
        if (crush) {
            throw new ExecuteScriptException("Произошла ошибка во время исполнения скрипта!");
        }
    }

    private boolean isNeedAnElement(Answer answer) {
        return answer.getType().toString().equals("NEED_ELEMENT");
    }

    private void rollbackEventsIfError() {
        if (crush) {
            manager.updateCollection(collectionReserveCopy);
        }
    }

}