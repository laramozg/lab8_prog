package client.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

public class MainFrame implements Runnable{
    private GUI gui;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable jTable = new JTable(tableModel);
    private RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
    private JFrame mainFrame = new JFrame("Lab 8");
    private JLabel user = new JLabel("");

    public MainFrame(GUI gui) {
        this.gui = gui;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JLabel getUser() {
        return user;
    }

    /**
     * Метод создает основной фрейм
     */
    public void createMainFrame() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLayout(new BorderLayout());
        JPanel headerBag = new JPanel();
        headerBag.setLayout(new GridBagLayout());
        headerBag.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        JPanel upperHeader = new JPanel();
        upperHeader.setLayout(new GridBagLayout());
        JButton exit = new JButton(gui.getBundle().getString("exit"));
        JComboBox<String> languages = new JComboBox<>(new String[]{"русский", "Eesti", "Català", "Español (Salvador)"});
        user.setText(gui.getAuthorization().getLogin());
        upperHeader.add(languages, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        upperHeader.add(user, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0));
        upperHeader.add(exit, new GridBagConstraints(2, 0, 1, 1, 0.09, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        upperHeader.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(2, 0, 5, 5));
        JPanel lowerHeader = new JPanel();
        lowerHeader.setLayout(new GridLayout(2, 0, 5, 5));
        String[] commands = {gui.getBundle().getString("clear"), gui.getBundle().getString("info"), gui.getBundle().getString("help"),
                gui.getBundle().getString("printFieldDescendingStartDate"), gui.getBundle().getString("filterGreaterThanPosition"),
                gui.getBundle().getString("replaceIfLowe"), gui.getBundle().getString("replaceIfGreater"),gui.getBundle().getString("insert"),
                gui.getBundle().getString("countByStartDate"), gui.getBundle().getString("removeKey"), gui.getBundle().getString("removeGreater"),
                gui.getBundle().getString("update")};
        String[] commandsAction = {"clear", "info", "help","print_field_descending_start_date", "filter_greater_than_position",
                "replace_if_lowe", "replace_if_greater", "insert", "count_by_start_date", "remove_key", "remove_greater", "update"};
        for (int i = 0; i < commands.length; i++) {
            JButton button = new JButton(commands[i]);
            button.setActionCommand(commandsAction[i]);
            lowerHeader.add(button);
            button.addActionListener(e -> {
                try {
                    switch (button.getActionCommand()) {
                        case "help":
                            gui.getResult().setText(gui.getBundle().getString("spravka"));
                            break;
                        case "clear":
                        case "info":
                        case "print_field_descending_start_date":
                            gui.getResult().setText(gui.getClient().handle(button.getActionCommand()));
                            break;
                        case "update":
                        case "replace_if_lowe":
                        case "replace_if_greater":
                        case "count_by_start_date":
                        case "filter_greater_than_position":
                        case "remove_greater":
                        case "remove_key":
                            gui.getAdd().createShowFrame(button.getActionCommand());
                            break;
                        case "insert":
                            gui.getAdd().createAddFrame(button.getActionCommand());
                            break;
                    }
                } catch (IndexOutOfBoundsException ex) {
                    // Исключение не мешает логике исполнения программы
                }
            });
        }
        upperHeader.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        lowerHeader.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));

        header.add(upperHeader);
        header.add(lowerHeader);

        header.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));

        headerBag.add(header, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
        mainFrame.add(headerBag, BorderLayout.NORTH);

        headerBag.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));

        String[] tableCol = new String[]{gui.getBundle().getString("key"),"id",gui.getBundle().getString( "name"), "X", "Y",
                gui.getBundle().getString("creationDate"), gui.getBundle().getString("salary"),gui.getBundle().getString("startDate"),
                gui.getBundle().getString("position"), gui.getBundle().getString("status"), gui.getBundle().getString("employeesCount"),
                gui.getBundle().getString("type"), gui.getBundle().getString("postalAddress"), gui.getBundle().getString("creator")};
        for (String s : tableCol) {
            tableModel.addColumn(s);
        }
        JPanel table = new JPanel();

        table.setLayout(new GridBagLayout());
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab(gui.getBundle().getString("table"), new JScrollPane(jTable));
        jTabbedPane.addTab(gui.getBundle().getString("visualisation"), new JScrollPane(gui.getGraphicsPanel()));
        jTable.setRowSorter(sorter);
        JPanel textArea = new JPanel();
        textArea.setLayout(new GridBagLayout());
        gui.getResult().setWrapStyleWord(true);
        gui.getResult().setEditable(false);

        table.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        textArea.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));

        JButton script = new JButton(gui.getBundle().getString("executeScript"));
        table.add(jTabbedPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 10, 5), 0, 0));
        textArea.add(new JScrollPane(gui.getResult()), new GridBagConstraints(0, 0, 1, 2, 1.0, 0.97,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 10, 0, 5), 0, 0));
        script.setActionCommand("execute_script");
        textArea.add(script, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.01,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 0, 5), 0, 0));
        table.add(textArea, new GridBagConstraints(1, 0, 1, 1, 0.5, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 10, 10), 0, 0));

        mainFrame.add(table);
        mainFrame.setLocationRelativeTo(null);
        exit.addActionListener(e -> {
            mainFrame.setVisible(false);
            gui.getAuthorization().getFrame().setVisible(true);
            gui.getResult().setText("");
        });

        script.addActionListener(e -> {
                gui.getAdd().createScript(script.getActionCommand());
        });

        languages.addActionListener(e -> {
            gui.choseLanguage(languages);
            exit.setText(gui.getBundle().getString("exit"));
            String[] commandsLanguage = {gui.getBundle().getString("clear"), gui.getBundle().getString("info"), gui.getBundle().getString("help"),
                    gui.getBundle().getString("printFieldDescendingStartDate"), gui.getBundle().getString("filterGreaterThanPosition"),
                    gui.getBundle().getString("replaceIfLowe"), gui.getBundle().getString("replaceIfGreater"),gui.getBundle().getString("insert"),
                    gui.getBundle().getString("countByStartDate"), gui.getBundle().getString("removeKey"), gui.getBundle().getString("removeGreater"), gui.getBundle().getString("update")};
            for (int i = 0; i < commandsLanguage.length; i++) {
                ((JButton) lowerHeader.getComponent(i)).setText(commandsLanguage[i]);
            }
            String[] tableLanguage = new String[]{gui.getBundle().getString("key"),"id",gui.getBundle().getString("name"),"x","y",gui.getBundle().getString("creationDate"),
                    gui.getBundle().getString("salary"),gui.getBundle().getString("startDate"),gui.getBundle().getString("position"),
                    gui.getBundle().getString("status"),gui.getBundle().getString("employeesCount"),gui.getBundle().getString("type"),
                    gui.getBundle().getString("postalAddress"),gui.getBundle().getString("creator")};
            Enumeration<TableColumn> enumeration = jTable.getColumnModel().getColumns();
            for (int i = 0; i < tableLanguage.length; i++) {
                enumeration.nextElement().setHeaderValue(tableLanguage[i]);
            }
            jTabbedPane.setTitleAt(0, gui.getBundle().getString("table"));
            jTabbedPane.setTitleAt(1, gui.getBundle().getString("visualisation"));
            script.setText(gui.getBundle().getString("executeScript"));
            gui.getResult().setText("");
        });
        mainFrame.setVisible(true);
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }
    /**
     * Метод загружает элементы
     */
    @Override
    public void run() {
        try {
            String answer;
            HashMap<String, Smiley> elementsServer = new HashMap<>();
            do {
                String condition = gui.getClient().handle("show");
                if (!condition.equals("Коллекция пуста")) {
                    Scanner scanner = new Scanner(condition);
                    elementsServer.clear();
                    do {
                        String elements = scanner.nextLine();
                        String[] arguments = elements.split(",");
                        tableModel.insertRow(0, arguments);
                        if (!gui.getGraphicsPanel().getColors().containsKey(arguments[13])) {
                            float[] rgb = gui.getGraphicsPanel().setColor();
                            gui.getGraphicsPanel().getColors().put(arguments[13], new Color(rgb[0], rgb[1], rgb[2]));
                        }
                        Smiley academicHat = new Smiley(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5],
                                arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11], arguments[12], arguments[13]);
                        elementsServer.put(arguments[0], academicHat);
                    } while (scanner.hasNextLine());
                    gui.getGraphicsPanel().updateElement(elementsServer);
                    do {
                        answer = gui.getClient().handle("show");
                        Thread.sleep(1000);
                    } while (answer.equals(condition));
                    for (int i = gui.getMain().getTableModel().getRowCount() - 1; i > -1; i--) {
                        gui.getMain().getTableModel().removeRow(i);
                    }
                } else {
                    elementsServer.clear();
                    gui.getGraphicsPanel().updateElement(elementsServer);
                    Thread.sleep(1000);
                }
            } while (true);
        } catch (InterruptedException | ArrayIndexOutOfBoundsException ex) {
            // Исключение не мешает логике исполнения программы
        }
    }

}