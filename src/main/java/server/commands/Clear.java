package server.commands;

import server.database.CollectionDatabase;
import utility.Answer;
import utility.interaction.Command;

/**
 * Класс события очистки коллекции
 */
public class Clear extends Action {

    @Override
    public Answer execute(Command command) {
        CollectionDatabase.getInstance().clear(command.getSender());
        manager.clear(command.getSender());
        return notifyAboutResult("Команда выполнена");
    }
}