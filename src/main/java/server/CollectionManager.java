package server;


import server.database.CollectionDatabase;
import utility.element.Position;
import utility.element.Worker;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для работы с коллекцией(исполнение команд)
 */
public class CollectionManager {

    private final LocalDate initialDate = LocalDate.now();

    ReentrantLock lock = new ReentrantLock();

    private Hashtable<String, Worker> collection = new Hashtable<>();


    public CollectionManager() throws SQLException {
        CollectionDatabase database;
        database = CollectionDatabase.getInstance();
        database.createTableIfNotExist();
        updateCollection(database.getData());

    }
    /**
     * Вывод доступных команд
     */
    public String help() {
        return "help - вывести справку по доступным командам\n" +
                "info - вывести в стандартный поток вывода информацию о коллекции\n" +
                "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert null - добавить новый элемент с заданным ключом\n" +
                "update id - обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key null - удалить элемент из коллекции по его ключу\n" +
                "clear - очистить коллекцию\n" +
                "save - сохранить коллекцию в файл\n" +
                "execute_script file_name - считать и исполнить скрипт из указанного файла\n" +
                "exit - завершить программу (без сохранения в файл)\n" +
                "remove_greater - удалить из коллекции все элементы, превышающие заданный\n" +
                "replace_if_greater null - заменить значение по ключу, если новое значение больше старого\n" +
                "replace_if_lowe null - заменить значение по ключу, если новое значение меньше старого\n" +
                "count_by_start_date startDate - вывести количество элементов, значение поля startDate которых равно заданному\n" +
                "filter_greater_than_position position - вывести элементы, значение поля position которого больше заданного\n" +
                "print_field_descending_start_date - вывести значения поля startDate всех элементов в порядке убывания";
    }

    public Hashtable<String, Worker> getCollection() {
        return collection;
    }

    /**
     * Вывод информации о коллекции
     */
    public String info() {
        return "Collection type:" + collection.getClass().toString() +
                "\nInitialization date " + initialDate +
                "\nCollection size " + collection.size();
    }

    /**
     * Показать элементы коллекции
     */
    public String show() {
        if (collection.size() != 0) {
            Stream<Worker> stream =collection.values().stream();
            return stream.map(Worker::toString).collect(Collectors.joining("\n"));
        } else {
            return  "Коллекция пуста";
        }
    }

    /**
     * Добавить элемент в коллекцию
     *
     * @param element Добавляемый элемент
     */
    public void insert(Worker element) {
        element.setCreationDate(LocalDate.now().toString());
        collection.put(element.getKey(), element);
    }

    /**
     * Обновить элемент коллекции по id
     *
     * @param element Добавляемый элемент
     * @param id      ID добавляемого элемента
     */
    public void update(int id, Worker element) {
        lock.lock();
        String key = getKeyById(id);
        element.setId(id);
        element.setCreationDate(LocalDate.now().toString());
        collection.put(key, element);
        lock.unlock();
    }


    /**
     * Удалить элемент коллекции по ключу
     *
     * @param key ключ удаляемого элемента
     */
    public void removeKey(String key) {
        collection.remove(key);
    }


    /**
     * Удалить элементы большие введённого элемента
     *
     * @param employeesCount Введённый элемент
     */
    public void removeGreater(Long employeesCount,String user) {
        lock.lock();
        Enumeration<String> key = collection.keys();
        while (key.hasMoreElements()) {
            String k = key.nextElement();
            if (collection.get(k).getOrganization().getEmployeesCount() > employeesCount && Objects.equals(collection.get(k).getCreator(), user)) {
                collection.remove(k);
            }
        }lock.unlock();
    }

    /**
     * Метод заменяет значение по ключу, если новое значение меньше старого
     *
     * @param key    ключ
     * @param worker элемент
     */
    public void replaceIfLowe(String key, Worker worker) {
        if (collection.containsKey(key)) {
            if (worker.getOrganization().getEmployeesCount() < collection.get(key).getOrganization().getEmployeesCount()) {
                collection.put(key, worker);
            }
        }
    }

    /**
     * Метод заменяет значение по ключу, если новое значение больше старого
     *
     * @param key    ключ
     * @param worker элемент
     */
    public void replaceIfGreater(String key, Worker worker) {
        if (collection.containsKey(key)) {
            if (worker.getOrganization().getEmployeesCount() > collection.get(key).getOrganization().getEmployeesCount()) {
                collection.put(key, worker);
            }
        }
    }

    /**
     * Метод выводит количество элементов, значение поля startDate которых равно заданному
     *
     * @param startDate Введённая дата
     */
    public String countByStartDate(Date startDate) {
        int count = 0;
        for (Worker p : collection.values()) {
            if (p.getStartDate().equals(startDate)) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    /**
     * Метод выводит элементы, значение поля position которого больше заданного
     *
     * @param position Введённая позиция
     */
    public String filterGreaterThanPosition(Position position) {
        lock.lock();
        int count = 0;
        StringBuilder list = new StringBuilder();
        Enumeration<String> key = collection.keys();
        while (key.hasMoreElements()) {
            String k = key.nextElement();
            if (collection.get(k).getPosition() != null) {
                if (collection.get(k).getPosition().toString().length() > position.toString().toUpperCase(Locale.ROOT).length()) {
                    count++;
                    list.append(collection.get(k)).append("\n");
                }
            }
        }
        if (count == 0) {
            return "0";
        }lock.unlock();
        return list.toString();
    }

    /**
     * Очистить коллекцию
     * @param user пользователь
     */
    public void clear(String user) {
        lock.lock();
        Enumeration<String> key = collection.keys();
        while (key.hasMoreElements()) {
            String k = key.nextElement();
            if (Objects.equals(collection.get(k).getCreator(), user)) {
                collection.remove(k);
                }
            }
        lock.unlock();
    }


    /**
     * Вывести поле позиция всех элементов коллекции
     */
    public String printFieldDescendingStartDate() {
        lock.lock();
        List<Date> date = new LinkedList<>();
        for (Worker worker : collection.values()) {
            date.add(worker.getStartDate());
        }
        Comparator<Date> comparator = Comparator.comparing(Date::getTime).reversed();
        date.sort(comparator);
        lock.unlock();
        return date.toString();
    }


    /**
     * Получить ключ по id
     *
     * @param id Введённый ID
     * @return ключ по ID
     */
    public String getKeyById(int id) {
        Enumeration<String> key = collection.keys();
        String k;
        while (key.hasMoreElements()) {
            k = key.nextElement();
            if (collection.get(k).getId() == id) {
                return k;
            }
        }
        return null;
    }


    /**
     * Заменить коллекцию
     */
    public void updateCollection(Hashtable<String, Worker> collection) {
        this.collection = new Hashtable<>(collection);
    }


}