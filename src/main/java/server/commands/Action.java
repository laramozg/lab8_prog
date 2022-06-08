package server.commands;

import org.postgresql.core.CommandCompleteParser;
import server.CollectionManager;
import utility.Answer;
import utility.AnswerType;
import utility.element.Worker;
import utility.interaction.Command;

/**
 * Общий класс для всех событий,
 * запускаемых командами
 */
public abstract class Action {
    protected CollectionManager manager;
    /**
     *Содержит количество слов в команде
     */

    public Answer needElement(){
        return new Answer(AnswerType.NEED_ELEMENT,"");
    }

    public Answer needParameter(){
        return new Answer(AnswerType.NEED_PARAMETER,"");
    }

    public Answer notifyAboutResult(String message){
        return new Answer(AnswerType.RETURN_ACTION,message);
    }

    public abstract Answer execute(Command command);

    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }


}