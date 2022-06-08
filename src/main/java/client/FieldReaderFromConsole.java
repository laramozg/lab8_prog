package client;

import utility.element.*;
import utility.exceptions.IncorrectArgumentException;
import utility.interaction.FieldReader;

import java.util.Date;
import java.util.Scanner;

public class FieldReaderFromConsole extends FieldReader {
    /**
     * Чтение элементов пользователя
     */
    public FieldReaderFromConsole(String[] argument) {
        super(argument);
    }

    public String keyReadEvent() {
    String key = null;
        try {
            key = keyValidation();
        } catch (IncorrectArgumentException e) {
            return e.getMessage();
        }
        return key;
    }

    public String nameReadEvent() {
        String name = null;
        try {
            name = nameValidation();
        } catch (IncorrectArgumentException e) {
            return e.getMessage();
        }
        return name;
    }

    public Coordinates coordinatesReadEvent() {
        Coordinates coordinates = null;
        try {
            coordinates = coordinatesValidation();
        } catch (NumberFormatException | IncorrectArgumentException e) {
            return null;
        }
        return coordinates;
    }

    public Float salaryReadEvent() {
        Float salary = null;
        try {
            salary = salaryValidation();
        } catch (NumberFormatException | IncorrectArgumentException e) {
            return null;
        }
        return salary;
    }


    public Date startDateReadEvent() {
        Date startDate;
        try {
            startDate = startDateValidation();
        } catch (IncorrectArgumentException e) {
           return null;
        }
        return startDate;
    }


    public Position positionReadEvent() {
        Position position = null;
        try {
            position = positionValidation();
        } catch (IllegalArgumentException e) {
            System.out.println("Введено некорректное значение!");
        }
        return position;
    }

    public Status statusReadEvent() {
        Status status = null;
        try {
            status = statusValidation();
        } catch (IllegalArgumentException e) {
            System.out.println("Введено некорректное значение!");
        }
        return status;
    }

    public long employeesCountReadEvent() {
        long employeesCount = 0;
        try {
            employeesCount = employeesCountValidation();
        } catch (NumberFormatException | IncorrectArgumentException e) {
            return 0;
        }
        return employeesCount;
    }

    public OrganizationType typeReadEvent() {
        OrganizationType type = null;
        try {
            type = typeValidation();
        } catch (IllegalArgumentException e) {
            return null;
        }
        return type;
    }

    public Address streetReadEvent(){
        Address street = null;
        try {
            street = streetValidation();
        } catch (IncorrectArgumentException e) {
            return null;
        }
        return street;
    }
}