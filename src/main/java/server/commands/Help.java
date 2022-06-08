package server.commands;

import utility.Answer;
import utility.interaction.Command;

/**
 * Класс события вывода всех команд
 */
public class Help extends Action {


    @Override
    public Answer execute(Command command) {
        return notifyAboutResult(manager.help());
    }
}