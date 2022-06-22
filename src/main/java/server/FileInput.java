package server;

import client.GUI.GUI;
import server.converter.FieldReaderFromFile;
import utility.element.*;
import utility.interaction.Command;
import utility.interaction.Input;

import java.util.Date;
import java.util.Scanner;

public class FileInput extends Input {
    Scanner read;
    public FileInput(Scanner reader) {
        super(reader);
    }

    @Override
    public Worker readElement(Command command, GUI gui) {
        String[] argument = {getReaders().nextLine().trim(),getReaders().nextLine().trim(),getReaders().nextLine().trim(),
                getReaders().nextLine().trim(),getReaders().nextLine().trim(),getReaders().nextLine().trim(),getReaders().nextLine().trim(),
                getReaders().nextLine().trim(),getReaders().nextLine().trim(),getReaders().nextLine().trim(),getReaders().nextLine().trim()};
        FieldReaderFromFile reader = new FieldReaderFromFile(argument);
        String key = reader.nameReadEvent();
        String name = reader.nameReadEvent();
        Coordinates coordinates = reader.coordinatesReadEvent();
        Float salary = reader.salaryReadEvent();
        Date startDate = reader.startDateReadEvent();
        Position position = reader.positionReadEvent();
        Status status = reader.statusReadEvent();
        long employeesCount = reader.employeesCountReadEvent();
        OrganizationType type = reader.typeReadEvent();
        Address street = reader.streetReadEvent();
        return new Worker(key,name, coordinates, salary, startDate, position, status, new Organization(employeesCount,type,street));
    }
}