package server.commands;

import server.database.CollectionDatabase;
import utility.Answer;
import utility.exceptions.IncorrectArgumentException;
import utility.interaction.Command;

public class RemoveKey extends Action{

    @Override
    public Answer execute(Command command) {
        String parameter = command.getParameter();
        if (parameter == null) {
            return needParameter();
        }
        checkKeyExist(parameter);
        CollectionDatabase.getInstance().checkElementAccess(parameter,command.getSender());
        CollectionDatabase.getInstance().removeByKey(parameter);
        manager.removeKey(parameter);
        return notifyAboutResult("Команда выполнена");
    }
    private void checkKeyExist(String key) {
        if (!manager.getCollection().containsKey(key)) {
            throw new IncorrectArgumentException("Такого ключа не существует!");
        }
    }
}
