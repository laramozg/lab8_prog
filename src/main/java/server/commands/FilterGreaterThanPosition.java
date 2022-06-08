package server.commands;

import utility.Answer;
import utility.element.Position;
import utility.interaction.Command;

/**
 * Выводит элементы, значение поля position которого больше заданного
 **/
public class FilterGreaterThanPosition extends Action{

    @Override
    public Answer execute(Command command){
        String parameter = command.getParameter();
        if (parameter == null) {
            return needParameter();
        }
        Position position = Position.valueOf(parameter);
        return notifyAboutResult(manager.filterGreaterThanPosition(position));
    }
}
