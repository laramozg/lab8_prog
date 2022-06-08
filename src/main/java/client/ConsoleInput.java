package client;

import client.GUI.GUI;
import utility.element.*;
import utility.interaction.Command;
import utility.interaction.Input;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleInput extends Input {
    String[] argument;
    public ConsoleInput(String reader) {
        super(reader);
    }

    @Override
    public Worker readElement(Command command,GUI gui) {

        argument = gui.getAdd().getResult();
        FieldReaderFromConsole reader = new FieldReaderFromConsole(argument);
        String key = reader.keyReadEvent();
        String name = reader.nameReadEvent();
        Coordinates coordinates = reader.coordinatesReadEvent();
        Float salary = reader.salaryReadEvent();
        Date startDate = reader.startDateReadEvent();
        Position position = reader.positionReadEvent();
        Status status = reader.statusReadEvent();
        long employeesCount = reader.employeesCountReadEvent();
        OrganizationType type = reader.typeReadEvent();
        Address street = reader.streetReadEvent();

        return new Worker(key, name, coordinates, salary, startDate, position, status, new Organization(employeesCount,type,street));
    }
}
