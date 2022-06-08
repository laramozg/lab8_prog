
package server.commands;

import utility.Answer;
import utility.AnswerType;
import utility.interaction.Command;

/**
 * Класс события выхода из программы
 */
public class Exit extends Action {


    @Override
    public Answer execute(Command command) {
        return new Answer(AnswerType.EXIT,"");
    }
}