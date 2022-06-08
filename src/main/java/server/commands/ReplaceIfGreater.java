package server.commands;

import server.database.CollectionDatabase;
import utility.Answer;
import utility.element.Worker;
import utility.exceptions.IncorrectArgumentException;
import utility.interaction.Command;

/**
 * Метод заменяет значение по ключу, если новое значение больше старого
 */
public class ReplaceIfGreater extends Action {
    @Override
    public Answer execute(Command command) {
        String parameter = command.getParameter();
        if (parameter == null) {
            return needParameter();
        }
        checkKeyExist(parameter);
        Worker element = command.getElement();
        CollectionDatabase.getInstance().checkElementAccess(parameter, command.getSender());
        if (element == null) {
            return needElement();
        }
        CollectionDatabase.getInstance().replaceIfGreater(parameter,element);
        manager.replaceIfGreater(parameter,element);
        return notifyAboutResult("Команда выполнена");
    }
    private void checkKeyExist(String key) {
        if (!manager.getCollection().containsKey(key)) {
            throw new IncorrectArgumentException("Такого ключа не существует!");
        }
    }
}