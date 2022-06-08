package server.commands;


import utility.Answer;
import utility.interaction.Command;

public class PrintFieldDescendingStartDate extends Action{

    @Override
    public Answer execute(Command command) {
        return notifyAboutResult(manager.printFieldDescendingStartDate());
    }
}

