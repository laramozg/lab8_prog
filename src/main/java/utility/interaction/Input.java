package utility.interaction;

import client.GUI.GUI;
import utility.element.Worker;
import utility.exceptions.IncorrectCommandException;

import java.util.Scanner;

public abstract class Input {
    private Scanner reader;
    private String readers;

    public Input(String readers) {
        this.readers = readers;
    }
    public Input(Scanner reader) {
        this.reader = reader;
    }
    public Scanner getReaders() {
        return reader;
    }

    public abstract Worker readElement(Command command,GUI gui);

    public boolean hasNext() {
        return reader.hasNext();
    }

    public Command readScriptCommand() {
        return new Command(reader.nextLine().trim(),null);
    }

    public Command readCommand() {
        return new Command(readers, null);
    }

}
