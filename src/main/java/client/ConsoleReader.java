package client;


import client.GUI.GUI;
import utility.element.Worker;
import utility.interaction.Command;
import utility.interaction.Input;

import java.util.Scanner;

/**
 * Класс получения и анализа данных из командной строки
 */
public class ConsoleReader {

    private Input input;


    public ConsoleReader(String reader) {
        this.input=new ConsoleInput(reader);
    }

    public Command readCommand(){return input.readCommand();}

    public Worker readElement(Command command,GUI gui) {
        return input.readElement(command,gui);
    }

    public String readParameter(Command command,GUI gui) {
        return gui.getAdd().getParameter();
    }
}
