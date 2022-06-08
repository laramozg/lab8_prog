package server.commands;

import server.database.CollectionDatabase;
import utility.Answer;
import utility.element.Worker;
import utility.exceptions.IncorrectArgumentException;
import utility.interaction.Command;

/**
 * Удалить элементы большие введённого элемента
 */
public class RemoveGreater extends Action{

    @Override
    public Answer execute(Command command) {
        String parameter = command.getParameter();
        if (parameter == null) {
            return needParameter();
        }
        long count;
        try {
            count = Long.parseLong(parameter);
            if (count<=0)throw new IncorrectArgumentException("Необходимо ввести число больше нуля!");
        }catch (IncorrectArgumentException e){
            throw new IncorrectArgumentException("Необходимо ввести число больше нуля!");
        };
        CollectionDatabase.getInstance().removeGreater(count,command.getSender());
        manager.removeGreater(count, command.getSender());
        return notifyAboutResult("Команда выполнена");
    }
}
