package server.commands;

import server.database.CollectionDatabase;
import utility.Answer;
import utility.element.Worker;
import utility.interaction.Command;

import java.sql.SQLException;

/**
 * Класс события добавления
 */
public class Insert extends Action {
    @Override
    public Answer execute(Command command) {
        Worker element = command.getElement();
        if (element == null) {
            return needElement();
        }
        try {
            element= CollectionDatabase.getInstance().insertElement(element);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        manager.insert(element);
        return notifyAboutResult("Команда выполнена");
    }
}