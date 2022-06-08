package client;

import client.GUI.GUI;
import utility.exceptions.IncorrectCommandException;

import java.io.IOException;

public class InteractionManager {

    InteractionStrategy strategy;
    InteractionClient client;
    ConsoleReader reader;
    GUI gui;
    String result;

    public InteractionManager(GUI gui,InteractionClient client, ConsoleReader reader){
        this.gui = gui;
        this.client=client;
        this.reader=reader;
    }

    public void setStrategy(InteractionStrategy strategy) {
        this.strategy = strategy;
    }

    public String handleAnswer(){
        try {
            StateConfiguration info= strategy.handleAnswer(gui,reader, client);
            if (!info.getMessage().equals("")){
                result = info.getMessage();
            }
            setStrategy(info.getStrategy());
        } catch (IOException e) {
            result = "Сервер временно недоступен.....";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IncorrectCommandException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
