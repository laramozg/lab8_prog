package server.commands;

import server.CollectionManager;
import utility.Answer;
import utility.UserData;
import utility.exceptions.IncorrectCommandException;
import utility.interaction.Command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Класс запуска события
 */
public class ActionInvoker {
    /**
     * Хэштаблица для хранения событий с командами, которые их запускают
     */
    private HashMap<String, Action> commands = new HashMap<>();
    public static ActionInvoker invoker;
    private CollectionManager manager;


    private ActionInvoker() {
    }


    public static ActionInvoker getInstance() throws SQLException {
        if (invoker == null) {
            invoker = new ActionInvoker();
            invoker.setManager(new CollectionManager());
        }
        return invoker;
    }

    public void setManager(CollectionManager manager) throws SQLException {
        this.manager = manager;
        invoker.addCommand("help", new Help());
        invoker.addCommand("insert", new Insert());
        invoker.addCommand("show", new Show());
        invoker.addCommand("count_by_start_date", new CountByStartDate());
        invoker.addCommand("clear", new Clear());
        invoker.addCommand("info", new Info());
        invoker.addCommand("update", new Update());
        invoker.addCommand("print_field_descending_start_date", new PrintFieldDescendingStartDate());
        invoker.addCommand("replace_if_lowe", new ReplaceIfLowe());
        invoker.addCommand("replace_if_greater", new ReplaceIfGreater());
        invoker.addCommand("remove_key", new RemoveKey());
        invoker.addCommand("remove_greater", new RemoveGreater());
        invoker.addCommand("filter_greater_than_position", new FilterGreaterThanPosition());
        invoker.addCommand("execute_script", new ExecuteScript());
        invoker.addCommand("exit", new Exit());
    }

    public void addCommand(String name, Action action) {
        action.setManager(manager);
        commands.put(name, action);
    }


    /**
     * Выбрать и выполнить команду
     *
     * @param command команда, которую исполняем
     */
    public Answer execute(Command command) {
        String commandName = command.getName();
        Action action = commands.get(commandName);
        return action.execute(command);
    }

}
