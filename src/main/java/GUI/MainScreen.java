package GUI;

import Database.DBconnector;
import Emailer.Mailer;
import Logger.MyLogger;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MainScreen {
    private MyLogger logger = new MyLogger();
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu customerMenu, workOrderMenu, specSheetMenu;
    private JPanel customerPanel, dataPanel, workOrderPanel, workDataPanel;
    private DBconnector db;
    private Document customerDocument, workOrderDocument;
    private JLabel firstName, lastName, hand, oval, leftFinger, rightFinger, leftReverse, rightReverse, leftForward, rightForward,
            leftSideway, rightSideway, bridge, span, cutToCutSpan, thumb, thumbType, thumbForward, thumbReverse, thumbLeft, thumbRight,
            degreeOfOval, width, grip;
    private JTextField firstNameField, lastNameField, handField, ovalField, leftFingerField, rightFingerField, leftReverseField,
            rightReverseField, leftForwardField, rightForwardField, leftSidewayField, rightSidewayField, bridgeField, spanField,
            cutToCutSpanField, thumbField, thumbTypeField, thumbForwardField, thumbReverseField, thumbLeftField, thumbRightField,
            degreeOfOvalField, widthField, gripField, searchBar, workSearch;
    private JLabel workName, workEmail, workBall, workNotes;
    private JTextField workNameField, workEmailField, workBallField;
    private JTextArea workNotesArea;
    private JButton submitButton;
    ArrayList<JLabel> labelArrayList;
    ArrayList<JTextField> fieldArrayList;
    private JTabbedPane mainTabbed;

    public MainScreen()
    {
        labelArrayList = new ArrayList<>();
        fieldArrayList = new ArrayList<>();
        frame = new JFrame("Lakeview Lanes Pro Shop");
        customerPanel = new JPanel();
        customerPanel.setLayout(new BorderLayout());
        menuBar = new JMenuBar();

        customerMenu = new JMenu("Customer");
        makeCustomerMenu();

        specSheetMenu = new JMenu("Spec Sheet Creation");
        makeSpecSheetMenu();

        workOrderMenu = new JMenu("Work Orders");
        makeWorkOrderMenu();

        workOrderPanel = new JPanel(new BorderLayout());
        workDataPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        workSearch = new JTextField();
        makeWorkSearchBar();
        makeWorkOrderPanel();
        workOrderPanel.add(workDataPanel, BorderLayout.CENTER);

        frame.setJMenuBar(menuBar);

        searchBar = new JTextField();
        makeSearchBar();


        dataPanel = new JPanel(new GridLayout(25, 2, 5, 5));
        makeDataPanel();
        customerPanel.add(dataPanel, BorderLayout.CENTER);

        mainTabbed = new JTabbedPane();
        mainTabbed.add("Spec Sheets", customerPanel);
        mainTabbed.add("Work Orders", workOrderPanel);

    }

    private void makeCustomerMenu()
    {
        customerMenu.getAccessibleContext().setAccessibleDescription("Menu for customer actions");
        menuBar.add(customerMenu);

        JMenuItem newCustomer = new JMenuItem("Create a Customer");
        newCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerTest addCustomer = new CustomerTest();
                addCustomer.make();
            }
        });
        customerMenu.add(newCustomer);

    }

    private void makeWorkOrderMenu()
    {
        menuBar.add(workOrderMenu);
        JMenuItem newWorkOrder = new JMenuItem("New Work Order");
        newWorkOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewWorkOrder workOrder = new NewWorkOrder();
                workOrder.make();
            }
        });
        workOrderMenu.add(newWorkOrder);
    }

    private void makeSpecSheetMenu()
    {
        menuBar.add(specSheetMenu);
        JMenuItem createSpec = new JMenuItem("Create/Edit a Spec Sheet");

        createSpec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateSpec cs = new CreateSpec();
                cs.make();
            }
        });

        specSheetMenu.add(createSpec);

    }

    private void makeWorkOrderPanel()
    {
        //Create the Labesls, and textfields
        workName = new JLabel("Name");
        workNameField = new JTextField();
        workNameField.setEditable(false);
        workBall = new JLabel("Ball");
        workBallField = new JTextField();
        workBallField.setEditable(false);
        workEmail = new JLabel("Email");
        workEmailField = new JTextField();
        workEmailField.setEditable(false);
        workNotes = new JLabel("Notes");
        workNotesArea = new JTextArea();
        submitButton = new JButton("Work Order Complete");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBconnector db = new DBconnector();
                db.remove(workOrderDocument, "WorkOrders");
                Mailer mailer = new Mailer(workOrderDocument.getString("email"));
                mailer.send(workOrderDocument.getString("ball"));
                workNameField.setText("");
                workBallField.setText("");
                workEmailField.setText("");
                workNotesArea.setText("");
            }
        });


        workDataPanel.add(workName);
        workDataPanel.add(workNameField);
        workDataPanel.add(workBall);
        workDataPanel.add(workBallField);
        workDataPanel.add(workEmail);
        workDataPanel.add(workEmailField);
        workDataPanel.add(workNotes);
        workDataPanel.add(workNotesArea);
        workDataPanel.add(submitButton);

    }

    private void makeSearchBar()
    {
        JPanel searchPanel = new JPanel(new GridLayout());
        customerPanel.add(searchPanel, BorderLayout.NORTH);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchBar, 0, 0);
        searchPanel.add(searchButton, 0, 1);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new DBconnector();
                String tempString = searchBar.getText();

                customerDocument = db.findDocument(tempString.split(" ")[0], tempString.split(" ")[1], "Customers");
                logger.makeLog(customerDocument.toString());
                db.closeConnection();
                fillData();
            }
        });
    }

    private void makeWorkSearchBar()
    {
        JPanel searchPanel = new JPanel(new GridLayout());
        workOrderPanel.add(searchPanel, BorderLayout.NORTH);
        JButton searchButton = new JButton("Search");
        searchPanel.add(workSearch, 0, 0);
        searchPanel.add(searchButton, 0, 1);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new DBconnector();
                String tempString = workSearch.getText();

                workOrderDocument = db.findDocument(tempString.split(" ")[0], tempString.split(" ")[1], "WorkOrders");
                logger.makeLog(workOrderDocument.toString());
                db.closeConnection();
                fillWorkOrderData();
            }
        });
    }

    private void fillWorkOrderData()
    {
        workNameField.setText(workOrderDocument.getString("firstName") + " " + workOrderDocument.getString("lastName"));
        workBallField.setText(workOrderDocument.getString("ball"));
        workEmailField.setText(workOrderDocument.getString("email"));
        workNotesArea.setText(workOrderDocument.getString("notes"));
    }

    public void make()
    {
        frame.setContentPane(new MainScreen().mainTabbed);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void fillData()
    {
        //Fill in all the fields.
        firstNameField.setText(customerDocument.getString("firstName"));
        lastNameField.setText(customerDocument.getString("lastName"));
        handField.setText(customerDocument.getString("hand"));
        leftFingerField.setText(customerDocument.getString("leftFinger"));
        rightFingerField.setText(customerDocument.getString("rightFinger"));
        leftReverseField.setText(customerDocument.getString("leftReverse"));
        rightReverseField.setText(customerDocument.getString("rightReverse"));
        leftForwardField.setText(customerDocument.getString("leftForward"));
        rightForwardField.setText(customerDocument.getString("rightForward"));
        leftSidewayField.setText(customerDocument.getString("leftSideway"));
        rightSidewayField.setText(customerDocument.getString("rightSideway"));
        bridgeField.setText(customerDocument.getString("bridge"));
        spanField.setText(customerDocument.getString("span"));
        cutToCutSpanField.setText(customerDocument.getString("cutToCut"));
        thumbTypeField.setText(customerDocument.getString("thumbType"));
        thumbForwardField.setText(customerDocument.getString("thumbForward"));
        thumbReverseField.setText(customerDocument.getString("thumbReverse"));
        thumbLeftField.setText(customerDocument.getString("thumbLeft"));
        thumbRightField.setText(customerDocument.getString("thumbRight"));
        degreeOfOvalField.setText(customerDocument.getString("degree"));
        widthField.setText(customerDocument.getString("width"));
        gripField.setText(customerDocument.getString("grip"));
        ovalField.setText(customerDocument.getString("oval"));
        thumbField.setText(customerDocument.getString("thumb"));
    }

    private void makeDataPanel()
    {
        //Create all the labels
        firstName = new JLabel("First Name:");
        labelArrayList.add(firstName);
        lastName = new JLabel("Last Name");
        labelArrayList.add(lastName);
        hand = new JLabel("Left/Right Hand");
        labelArrayList.add(hand);
        oval = new JLabel("Oval");
        labelArrayList.add(oval);
        leftFinger = new JLabel("Left Finger");
        labelArrayList.add(leftFinger);
        rightFinger = new JLabel("Right Finger");
        labelArrayList.add(rightFinger);
        leftReverse = new JLabel("Left Reverse Pitch");
        labelArrayList.add(leftReverse);
        rightReverse = new JLabel("Right Reverse Pitch");
        labelArrayList.add(rightReverse);
        leftForward = new JLabel("Left Forward Pitch");
        labelArrayList.add(leftForward);
        rightForward = new JLabel("Right Forward Pitch");
        labelArrayList.add(rightForward);
        leftSideway = new JLabel("Left Lateral Pitch");
        labelArrayList.add(leftSideway);
        rightSideway = new JLabel("Right Lateral Pitch");
        labelArrayList.add(rightSideway);
        bridge = new JLabel("Bridge");
        labelArrayList.add(bridge);
        span = new JLabel("Span");
        labelArrayList.add(span);
        cutToCutSpan = new JLabel("Cut to Cut Span");
        labelArrayList.add(cutToCutSpan);
        thumb = new JLabel("Thumb");
        labelArrayList.add(thumb);
        thumbType = new JLabel("Thumb Type");
        labelArrayList.add(thumbType);
        thumbForward = new JLabel("Thumb Forward Pitch");
        labelArrayList.add(thumbForward);
        thumbReverse = new JLabel("Thumb Reverse Pitch");
        labelArrayList.add(thumbReverse);
        thumbLeft = new JLabel("Thumb Left Pitch");
        labelArrayList.add(thumbLeft);
        thumbRight = new JLabel("Thumb Right Pitch");
        labelArrayList.add(thumbRight);
        degreeOfOval = new JLabel("Degree of Oval");
        labelArrayList.add(degreeOfOval);
        width = new JLabel("Width of Oval");
        labelArrayList.add(width);
        grip = new JLabel("Grip");
        labelArrayList.add(grip);

        //Create all the text fields
        firstNameField = new JTextField();
        fieldArrayList.add(firstNameField);
        lastNameField = new JTextField();
        fieldArrayList.add(lastNameField);
        handField = new JTextField();
        fieldArrayList.add(handField);
        ovalField = new JTextField();
        fieldArrayList.add(ovalField);
        leftFingerField = new JTextField();
        fieldArrayList.add(leftFingerField);
        rightFingerField = new JTextField();
        fieldArrayList.add(rightFingerField);
        leftReverseField = new JTextField();
        fieldArrayList.add(leftReverseField);
        rightReverseField = new JTextField();
        fieldArrayList.add(rightReverseField);
        leftForwardField = new JTextField();
        fieldArrayList.add(leftForwardField);
        rightForwardField = new JTextField();
        fieldArrayList.add(rightForwardField);
        leftSidewayField = new JTextField();
        fieldArrayList.add(leftSidewayField);
        rightSidewayField = new JTextField();
        fieldArrayList.add(rightSidewayField);
        bridgeField = new JTextField();
        fieldArrayList.add(bridgeField);
        spanField = new JTextField();
        fieldArrayList.add(spanField);
        cutToCutSpanField = new JTextField();
        fieldArrayList.add(cutToCutSpanField);
        thumbField = new JTextField();
        thumbTypeField = new JTextField();
        fieldArrayList.add(thumbTypeField);
        fieldArrayList.add(thumbField);
        thumbForwardField = new JTextField();
        fieldArrayList.add(thumbForwardField);
        thumbReverseField = new JTextField();
        fieldArrayList.add(thumbReverseField);
        thumbLeftField = new JTextField();
        fieldArrayList.add(thumbLeftField);
        thumbRightField = new JTextField();
        fieldArrayList.add(thumbRightField);
        degreeOfOvalField = new JTextField();
        fieldArrayList.add(degreeOfOvalField);
        widthField = new JTextField();
        fieldArrayList.add(widthField);
        gripField = new JTextField();
        fieldArrayList.add(gripField);

        //Add everything to the panel.
        for(int i = 0; i < fieldArrayList.size(); i++)
        {
            dataPanel.add(labelArrayList.get(i));
            fieldArrayList.get(i).setEditable(false);
            dataPanel.add(fieldArrayList.get(i));
        }

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < fieldArrayList.size(); i++)
                {
                    fieldArrayList.get(i).setText("");
                    searchBar.setText("");
                }
            }
        });

        dataPanel.add(clearButton);
    }

}
