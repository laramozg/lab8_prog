package utility.interaction;

import utility.element.Worker;

import java.io.Serializable;

public class Command implements Serializable {
    private String name;
    private String parameter;
    private Worker element;
    private String sender;

    public Command(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    public void setElement(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public String getParameter() {
        return parameter;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        if (parameter != null) {
            return name + " " + parameter;
        }
        return name;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}