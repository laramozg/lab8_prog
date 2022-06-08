package server.commands;

import server.database.CollectionDatabase;
import utility.Answer;
import utility.UserData;
import utility.element.Worker;
import utility.exceptions.IncorrectArgumentException;
import utility.interaction.Command;

import java.util.concurrent.locks.ReentrantLock;

public class Update extends Action {
    @Override
    public Answer execute(Command command) {
        String i = command.getParameter();
        if (i == null) {
            return needParameter();
        }
        int id = 0;
        try {
           id = Integer.parseInt(i);
        }catch (IncorrectArgumentException e){
            throw new IncorrectArgumentException("Необходимо ввести число!");
        };
        checkIdExist(id);
        Worker element = command.getElement();
        CollectionDatabase.getInstance().checkElementAccess(id, command.getSender());
        if (element == null) {
            return needElement();
        }
        CollectionDatabase.getInstance().updateElement(id, element);
        manager.update(id, element);
        return notifyAboutResult("Команда выполнена");
    }

    private void checkIdExist(int id) {
        if (manager.getKeyById(id) == null) {
            throw new IncorrectArgumentException("Такого id не существует!");
        }
    }

}
