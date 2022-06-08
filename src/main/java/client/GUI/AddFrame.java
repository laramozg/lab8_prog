package client.GUI;

import client.FieldReaderFromConsole;
import utility.element.*;
import utility.interaction.Command;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class AddFrame {
    private GUI gui;
    private JLabel validate = new JLabel();
    String[] arguments;
    String par;
    public AddFrame(GUI gui) {
        this.gui = gui;
    }
    public JLabel getValidate() {
        return validate;
    }
    public JButton buttonAuth;
    /**
     * Метод создает фрейм для ввода данных
     *
     * @param command
     */
    public void createAddFrame(String command) {
        validate.setText("");
        int count = 0;
        JLabel results = new JLabel();
        JFrame addFrame = new JFrame("Lab 8");
        addFrame.getContentPane().setBackground(Color.getHSBColor((float) 0.7,(float)0.43,(float)1.0));
        addFrame.setLayout(new GridBagLayout());
        addFrame.setSize(700, 900);
        addFrame.setDefaultCloseOperation(addFrame.DISPOSE_ON_CLOSE);
        addFrame.setResizable(false);
        addFrame.setLocationRelativeTo(null);
        addFrame.getContentPane().setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        JTextField keyFiled = new JTextField();
        JTextField nameFiled = new JTextField();
        JTextField xFiled = new JTextField();
        JTextField yFiled = new JTextField();
        JTextField salaryFiled = new JTextField();
        JTextField startDateFiled = new JTextField();
        ButtonGroup position = new ButtonGroup();
        JRadioButton fof1 = new JRadioButton("HUMAN_RESOURCES");
        JRadioButton fof2 = new JRadioButton("HEAD_OF_DIVISION");
        JRadioButton fof3 = new JRadioButton("DEVELOPER");
        fof1.setActionCommand("HUMAN_RESOURCES");
        fof2.setActionCommand("HEAD_OF_DIVISION");
        fof3.setActionCommand("DEVELOPER");
        fof1.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        fof2.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        fof3.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        position.add(fof1);
        position.add(fof2);
        position.add(fof3);
        JPanel fof = new JPanel();
        fof.setLayout(new GridLayout(3, 1, 5, 5));
        fof.add(fof1);
        fof.add(fof2);
        fof.add(fof3);
        fof.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        ButtonGroup status = new ButtonGroup();
        JRadioButton s1 = new JRadioButton("FIRED");
        JRadioButton s2 = new JRadioButton("HIRED");
        JRadioButton s3 = new JRadioButton("RECOMMENDED_FOR_PROMOTION");
        JRadioButton s4 = new JRadioButton("REGULAR");
        JRadioButton s5 = new JRadioButton("PROBATION");
        s1.setActionCommand("FIRED");
        s2.setActionCommand("HIRED");
        s3.setActionCommand("RECOMMENDED_FOR_PROMOTION");
        s4.setActionCommand("REGULAR");
        s5.setActionCommand("PROBATION");
        s1.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        s2.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        s3.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        s4.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        s5.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        status.add(s1);
        status.add(s2);
        status.add(s3);
        status.add(s4);
        status.add(s5);
        JPanel s = new JPanel();
        s.setLayout(new GridLayout(3, 1, 5, 5));
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5);
        s.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        JTextField employeesFiled = new JTextField();
        JTextField streetField = new JTextField();
        ButtonGroup organizationType = new ButtonGroup();
        JRadioButton c1 = new JRadioButton("COMMERCIAL");
        JRadioButton c2 = new JRadioButton("GOVERNMENT");
        JRadioButton c3 = new JRadioButton("OPEN_JOINT_STOCK_COMPANY");
        c1.setActionCommand("COMMERCIAL");
        c2.setActionCommand("GOVERNMENT");
        c3.setActionCommand("OPEN_JOINT_STOCK_COMPANY");
        c1.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        c2.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        c3.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        organizationType.add(c1);
        organizationType.add(c2);
        organizationType.add(c3);
        JPanel c = new JPanel();
        c.setLayout(new GridLayout(3, 1, 5, 5));
        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        buttonAuth = new JButton(gui.getBundle().getString("submit"));
        createComponentForAdd(addFrame, gui.getBundle().getString("key"), keyFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("name"), nameFiled, count++);
        createComponentForAdd(addFrame, "x: ", xFiled, count++);
        createComponentForAdd(addFrame, "y: ", yFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("salary"), salaryFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("startDate"), startDateFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("position"), fof, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("status"), s, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("employeesCount"), employeesFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("type"), c, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("postalAddress"), streetField, count++);
        createComponentForAdd(addFrame, "", validate, count++);
        addFrame.add(buttonAuth, new GridBagConstraints(0, count, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 50, 20, 0), 0, 0));
        addFrame.add(results, new GridBagConstraints(1, count, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 50, 20, 0), 0, 0));
        buttonAuth.addActionListener(e -> {
            String positions;
            String statuses;
            String type;
            try {
                positions = position.getSelection().getActionCommand();
            } catch (NullPointerException ex) {
                positions = null;
            }
            try {
                statuses = status.getSelection().getActionCommand();
            } catch (NullPointerException ex) {
                statuses = null;
            }
            try {
                type = organizationType.getSelection().getActionCommand();
            } catch (NullPointerException ex) {
                type = null;
            }


            arguments = new String[]{keyFiled.getText(),nameFiled.getText(), xFiled.getText(), yFiled.getText(), salaryFiled.getText(), startDateFiled.getText(),
                    positions, statuses, employeesFiled.getText(), type, streetField.getText()};
            FieldReaderFromConsole reader = new FieldReaderFromConsole(arguments);
            if (reader.keyReadEvent()==null){
                results.setText(gui.getBundle().getString("keyEr"));
            }
            else if (reader.nameReadEvent()==null){
                results.setText(gui.getBundle().getString("nameEr"));
            }
            else if (reader.coordinatesReadEvent()==null){
                results.setText(gui.getBundle().getString("coordinatesEr"));
            }
            else if (reader.salaryReadEvent()==null){
                results.setText(gui.getBundle().getString("salaryEr"));
            }
            else if (reader.startDateReadEvent()==null){
                results.setText(gui.getBundle().getString("dateEr"));
            }
            else if (reader.employeesCountReadEvent()==0){
                results.setText(gui.getBundle().getString("employeesEr"));
            }
            else if (reader.streetReadEvent()==null){
                results.setText(gui.getBundle().getString("addressEr"));
            }
            else {
                try {
                    switch (command) {
                        case "insert": {
                            String result = gui.getClient().handle(command);
                            gui.getResult().setText(gui.getLocalization().localize(result));
                            addFrame.dispose();
                            break;
                        }
                        case "update":
                        case "replace_if_lowe":
                        case "replace_if_greater": {
                            String result = gui.getClient().handle(command);
                            gui.getResult().setText(gui.getLocalization().localize(result));
                            addFrame.dispose();
                        }
                    }
                } catch (NumberFormatException ex) {
                    validate.setText(gui.getBundle().getString("emptyFields"));
                }
            }
        });
        addFrame.setVisible(true);
    }

    public String[] getResult(){
        return arguments;
    }
    public String getParameter(){
        return par;
    }

    /**
     * Метод добавляет элементы на фрейм
     *
     */
    public void createShowFrame(String command) {
        int c = 0;
        JFrame parameter = new JFrame("parameter");
        parameter.setLayout(new GridBagLayout());
        parameter.setSize(400,200);
        parameter.setDefaultCloseOperation(parameter.DISPOSE_ON_CLOSE);
        parameter.setResizable(false);
        parameter.setLocationRelativeTo(null);
        parameter.getContentPane().setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
        if (Objects.equals(command, "filter_greater_than_position")){
            ButtonGroup position = new ButtonGroup();
            JRadioButton fof1 = new JRadioButton("HUMAN_RESOURCES");
            JRadioButton fof2 = new JRadioButton("HEAD_OF_DIVISION");
            JRadioButton fof3 = new JRadioButton("DEVELOPER");
            fof1.setActionCommand("HUMAN_RESOURCES");
            fof2.setActionCommand("HEAD_OF_DIVISION");
            fof3.setActionCommand("DEVELOPER");
            fof1.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
            fof2.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
            fof3.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
            position.add(fof1);
            position.add(fof2);
            position.add(fof3);
            JPanel fof = new JPanel();
            fof.setLayout(new GridLayout(3, 1, 5, 5));
            fof.add(fof1);
            fof.add(fof2);
            fof.add(fof3);
            fof.setBackground(Color.getHSBColor((float) 0.75,(float)0.24,(float)1.0));
            createComponentForAdd(parameter, gui.getBundle().getString("position"), fof, c++);
            createComponentForAdd(parameter, "", validate, c++);
            JButton buttonAuth = new JButton("submit");
            parameter.add(buttonAuth, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 50, 20, 0), 0, 0));
            buttonAuth.addActionListener(e -> {
                try {
                    par = position.getSelection().getActionCommand();
                    String result = gui.getClient().handle(command);
                    gui.getResult().setText(result);
                    parameter.dispose();
                }catch (NullPointerException er){
                    gui.getResult().setText(gui.getBundle().getString("parameterER"));
                }
        });}
        else {
            JTextField idF = new JTextField();
            if (Objects.equals(command, "update")) {
                createComponentForAdd(parameter,"id",idF,c++);}
            if (Objects.equals(command, "count_by_start_date")) {
                createComponentForAdd(parameter,gui.getBundle().getString("startDate"),idF,c++);}
            if (Objects.equals(command, "remove_greater")) {
                createComponentForAdd(parameter,gui.getBundle().getString("employeesCount"),idF,c++);}
            if (Objects.equals(command, "replace_if_lowe") || Objects.equals(command, "replace_if_greater") || Objects.equals(command, "remove_key")) {
                createComponentForAdd(parameter,gui.getBundle().getString("key"),idF,c++);}
                createComponentForAdd(parameter, "", validate, c++);
            JButton buttonAuth = new JButton(gui.getBundle().getString("submit"));
            parameter.add(buttonAuth, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 50, 20, 0), 0, 0));
            buttonAuth.addActionListener(e -> {
                try {
                    par = idF.getText();
                    String result = gui.getClient().handle(command);
                    if (Objects.equals(command, "count_by_start_date")) {
                        gui.getResult().setText(result);
                    } else gui.getResult().setText(gui.getLocalization().localize(result));
                    parameter.dispose();
                }catch (NullPointerException er){
                    gui.getResult().setText(gui.getBundle().getString("parameterER"));
                }
            });}
        parameter.setVisible(true);
    }

    public void createScript(String command){
        try {
            JFileChooser file = new JFileChooser();
            JButton open = new JButton();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (file.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            }
            par = file.getSelectedFile().getAbsolutePath();
            gui.getResult().setText(gui.getClient().handle(command));
        }catch (NullPointerException ex) {
            // Исключение не мешает логике исполнения программы
        }
    }

    private void createComponentForAdd(JFrame addFrame, String arg1, JComponent arg2, int position) {
        addFrame.add(new JLabel(arg1), new GridBagConstraints(0, position, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(20, 40, 0, 0), 0, 0));
        addFrame.add(arg2, new GridBagConstraints(1, position, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 10), 0, 0));
    }


}