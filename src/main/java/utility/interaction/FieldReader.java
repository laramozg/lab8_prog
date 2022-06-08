package utility.interaction;

import utility.element.*;
import utility.exceptions.IncorrectArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Класс чтения полей ввода
 */
public abstract class FieldReader {
    String[] argument;

    public FieldReader(String[] argument) {
        this.argument = argument;
    }

    public String keyValidation() {
        String key = argument[0];
        if (key.trim().isEmpty()) {
            return null;
        }
        return key;
    }

    public String nameValidation() {
        String name = argument[1];
        if (name.trim().isEmpty()) {
            return null;
        }
        return name;
    }

    public Coordinates coordinatesValidation() {
        Coordinates coordinates;
        Double x = Double.parseDouble(argument[2]);
        double y = Double.parseDouble(argument[3]);
        coordinates = new Coordinates(x, y);
        if (!coordinates.isValid()) {
            return null;
        }
        return coordinates;
    }

    public Float salaryValidation() {
        String salaryLine = argument[4];
        if (salaryLine.trim().isEmpty()) {
            return null;
        }
        Float salary = null;
        if (!salaryLine.isEmpty()) {
            salary = Float.parseFloat(salaryLine);
            if (salary <= 0) {
                return null;
            }
        }
        return salary;
    }

    public Date startDateValidation() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = argument[5];
        if (date.trim().isEmpty()) {
            return null;
        }
        Date startDate;
        try {
            formatForDateNow.setLenient(false);
            startDate = formatForDateNow.parse(date);
            if (startDate.after(new Date())){
                return null;
            }
        }catch (ParseException e) {
            return null;}
        return startDate;

    }

    public Position positionValidation() {
        String positionLine = argument[6].toUpperCase();
        Position position = null;
        if (!positionLine.isEmpty()) {
            position = Position.valueOf(positionLine);
        }
        return position;
    }


    public Status statusValidation() {
        Status status = null;
        String statusLine = argument[7].toUpperCase();
        if (!statusLine.isEmpty()) {
            status = Status.valueOf(statusLine);
        }
        return status;
    }

    public long employeesCountValidation() {
        String countLine = argument[8];
        long employeesCount = Long.parseLong(countLine);
        if (employeesCount <= 0 ||countLine.trim().isEmpty() ) {
            return 0;
        }
        return employeesCount;
    }

    public OrganizationType typeValidation() {
        OrganizationType type;
        String typeLine = argument[9].toUpperCase();
        type = OrganizationType.valueOf(typeLine);
        if (typeLine.trim().isEmpty()) {
            return null;
        }
        return type;
    }

    public Address streetValidation() {
        String street = argument[10];
        if (street.trim().isEmpty() || !new Address(street).isValid()) {
            return null;
        }
        return new Address(street);
    }


}