package client;

import client.GUI.GUI;

import java.io.IOException;

public interface InteractionStrategy {

    StateConfiguration handleAnswer(GUI gui,ConsoleReader reader, InteractionClient client) throws IOException, ClassNotFoundException;
}
