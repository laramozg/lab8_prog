package server.commands;

import utility.Answer;
import utility.interaction.Command;

/**
 * Класс события вывода коллекции
 */
public class Show extends Action {

    @Override
    public Answer execute(Command command) {
        return notifyAboutResult(manager.show());
    }
}