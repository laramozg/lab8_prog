package client;

import client.GUI.GUI;
import utility.Answer;
import utility.AnswerType;
import utility.Request;
import utility.RequestType;
import utility.element.Worker;
import utility.exceptions.ExitException;
import utility.interaction.Command;

import java.io.IOException;

public class DialogStrategy implements InteractionStrategy {
    int s = 0;
    @Override
    public StateConfiguration handleAnswer(GUI gui, ConsoleReader reader, InteractionClient client) throws IOException {
        Command command = reader.readCommand();
        command.setSender(client.userData.getLogin());
        Request request=new Request(RequestType.COMMAND_EXECUTION,command);
        client.sendRequest(request);
        Answer answer = client.getAnswer();
        switch (answer.getType()) {
            case EXIT:
                throw new ExitException("Exit");
            case RETURN_ACTION:
                if (!answer.getMessage().equals("")) {
                    return new StateConfiguration(answer.getMessage(),new DialogStrategy());
                }
                break;
            case NEED_PARAMETER:
                String parameter = reader.readParameter(command,gui);
                command.setParameter(parameter);
                request=new Request(RequestType.COMMAND_EXECUTION,command);
                client.sendRequest(request);
                answer = client.getAnswer();
                if (!answer.getType().equals(AnswerType.NEED_ELEMENT)) {
                    return new StateConfiguration(answer.getMessage(), new DialogStrategy());
                }
                if (s == 0){
                    s++;
                    gui.getAdd().createAddFrame(command.getName());
                    return new StateConfiguration(answer.getMessage(), new DialogStrategy());
                }
            case NEED_ELEMENT:
                Worker worker = reader.readElement(command,gui);
                command.setElement(worker);
                request=new Request(RequestType.COMMAND_EXECUTION,command);
                client.sendRequest(request);
                answer = client.getAnswer();
                return new StateConfiguration(answer.getMessage(),new DialogStrategy());
            case GETTER_STATE:
                return new StateConfiguration("",new ReceiverStrategy());
        }
        return new StateConfiguration(answer.getMessage(),new DialogStrategy());
    }
}
