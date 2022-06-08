package client;

import client.GUI.GUI;
import utility.*;

import java.io.IOException;

public class AuthorizationState implements InteractionStrategy{
    GUI gui = new GUI();
    @Override
    public StateConfiguration handleAnswer(GUI gui,ConsoleReader reader, InteractionClient client) throws IOException {
        String login;
        String password;
        login = gui.getAuthorization().getLogin();
        password = gui.getAuthorization().getPassword();
        UserData userData = new UserData(login, password);
        client.setUserData(userData);
        Request request = new Request(RequestType.AUTHORIZATION_REQUEST);
        client.sendRequest(request);
        Answer answer=client.getAnswer();
        if (answer.getType()== AnswerType.SUCCESSFULLY){
            return new StateConfiguration("SUCCESSFULLY",new DialogStrategy());
        }
        else{
            return new StateConfiguration(answer.getMessage(),new AuthorizationState());
        }

    }
}
