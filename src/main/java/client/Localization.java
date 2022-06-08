package client;

import client.GUI.GUI;

public class Localization {
    private GUI gui;

    public Localization(GUI gui) {
        this.gui = gui;
    }

    public String localize(String str) {
        switch (str) {
            case "Пароль или логин не может быть null":
            case "Данный логин недопустим!":
                return gui.getBundle().getString("notNull");
            case "Данный логин занят!":
                return gui.getBundle().getString("loginExit");
            case "Пароль или логин неверен!":
                return gui.getBundle().getString("authorizationEx");
            case "Аргумент команды некорректен!":
                return gui.getBundle().getString("parameterER");
            case "Такого id не существует!":
                return gui.getBundle().getString("idNot");
            case "Этот элемент Вам не принадлежит!":
                return gui.getBundle().getString("notYours");
            case "Такого ключа не существует!":
                return gui.getBundle().getString("keyNot");
            case "Дату необходимо ввести в формате год/месяц/день часы:минуты:секунды!":
                return gui.getBundle().getString("dateEr");
            case "Необходимо ввести число больше нуля!":
                return gui.getBundle().getString("moreZero");
            case "Коллекция пуста":
                return gui.getBundle().getString("isEmpty");
            case "Команда выполнена":
                return gui.getBundle().getString("done");
            case "Необходимо ввести число!":
                return gui.getBundle().getString("needNumber");
            default:
                return str;
        }
    }
}
