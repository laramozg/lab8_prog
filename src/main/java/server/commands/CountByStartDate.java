package server.commands;

import utility.Answer;
import utility.exceptions.IncorrectArgumentException;
import utility.interaction.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Выводит количество элементов, значение поля startDate которых равно заданному
 **/
public class CountByStartDate extends Action{

    @Override
    public Answer execute(Command command) {
        try{
            String parameter = command.getParameter();
            if (parameter == null) {
                return needParameter();
            }
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date start_date;
            start_date = formatForDateNow.parse(parameter);
            return notifyAboutResult(manager.countByStartDate(start_date));
        }catch (ParseException e) {
            throw new IncorrectArgumentException("Дату необходимо ввести в формате год/месяц/день часы:минуты:секунды!");
        }
    }
}
