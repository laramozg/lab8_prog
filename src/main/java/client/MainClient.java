package client;

import client.GUI.GUI;
import utility.exceptions.ExitException;

import java.util.ResourceBundle;


public class MainClient {
    private static GUI gui;
    static InteractionClient client;
    String result;
    InteractionStrategy strategy;
    public static void main(String[] args) {
        client = new InteractionClient("localhost", 3001);
        gui = new GUI();
        gui.bundle = ResourceBundle.getBundle("resources");
        gui.getAuthorization().createAuthorizationFrame();
    }

    public String handle(String reader){
        ConsoleReader read = new ConsoleReader(reader);
        InteractionManager manager = new InteractionManager(gui,client,read);

        if (reader.equals("auth")){
            manager.setStrategy(new AuthorizationState());
            result=manager.handleAnswer();
            strategy = manager.strategy;
        }
        else if (reader.equals("reg")){
            manager.setStrategy(new RegistrationState());
            result=manager.handleAnswer();
            strategy = manager.strategy;
        }
        else{
            try {
                manager.setStrategy(strategy);
                result = manager.handleAnswer();
            }
            catch (ExitException e){

            }
        };
    return result;
}}

