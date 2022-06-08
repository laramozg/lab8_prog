package client.GUI;

import client.Localization;
import client.MainClient;


import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUI {
    public ResourceBundle bundle;
    private MainClient client = new MainClient();
    private AuthorizationFrame authorization = new AuthorizationFrame(this);
    private MainFrame main = new MainFrame(this);
    private AddFrame add = new AddFrame(this);
    private GraphicsPanel graphicsPanel = new GraphicsPanel(this);
    private Localization localization = new Localization(this);
    private JTextArea result = new JTextArea();

    public ResourceBundle getBundle() {
        return bundle;
    }

    public MainClient getClient() {
        return client;
    }

    public AuthorizationFrame getAuthorization() {
        return authorization;
    }

    public MainFrame getMain() {
        return main;
    }

    public AddFrame getAdd() {
        return add;
    }

    public GraphicsPanel getGraphicsPanel() {
        return graphicsPanel;
    }

    public Localization getLocalization() {
        return localization;
    }

    public JTextArea getResult() {
        return result;
    }

    /**
     * Метод выбирает локализация программы
     */
    public void choseLanguage(JComboBox<String> languages) {
        String language = (String) languages.getSelectedItem();
        switch (language) {
            case "русский":
                bundle = ResourceBundle.getBundle("resources");
                break;
            case "Español (Salvador)":
                bundle = ResourceBundle.getBundle("resources", new Locale("es_SV"));
                break;
            case "Eesti":
                bundle = ResourceBundle.getBundle("resources", new Locale("et_EE"));
                break;
            case "Català":
                bundle = ResourceBundle.getBundle("resources", new Locale("ca_ES"));
                break;
        }
    }
}