package server.commands;

import utility.Answer;
import utility.interaction.Command;

/**
 * Класс события вывода информации о коллекции
 */
public class Info extends Action {

    @Override
    public Answer execute(Command command) {
        return notifyAboutResult(manager.info());
    }
}