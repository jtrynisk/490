package GUI;

import Customer.SpecSheet;
import Database.DBconnector;
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
    private JPanel customerPanel, dataPanel;
    private DBconnector db;
    private Document customerDocument;
    private JLabel firstName, lastName, hand, oval, leftFinger, rightFinger, leftReverse, rightReverse, leftForward, rightForward,
            leftSideway, rightSideway, bridge, span, cutToCutSpan, thumb, thumbForward, thumbReverse, thumbLeft, thumbRight,
            degreeOfOval, width, grip;
    private JTextField firstNameField, lastNameField, handField, ovalField, leftFingerField, rightFingerField, leftReverseField,
            rightReverseField, leftForwardField, rightForwardField, leftSidewayField, rightSidewayField, bridgeField, spanField,
            cutToCutSpanField, thumbField, thumbForwardField, thumbReverseField, thumbLeftField, thumbRightField,
            degreeOfOvalField, widthField, gripField;
    ArrayList<JLabel> labelArrayList;
    ArrayList<JTextField> fieldArrayList;

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

        frame.setJMenuBar(menuBar);

        makeSearchBar();

        dataPanel = new JPanel(new GridLayout(23, 2, 5, 5));
        makeDataPanel();
        customerPanel.add(dataPanel, BorderLayout.CENTER);
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

        JMenuItem searchCustomer = new JMenuItem("Search Customers");
        searchCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void makeWorkOrderMenu()
    {
        menuBar.add(workOrderMenu);
        JMenuItem newWorkOrder = new JMenuItem("New Work Order");
        newWorkOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Make a work order
            }
        });
        workOrderMenu.add(newWorkOrder);
    }

    private void makeSpecSheetMenu()
    {
        menuBar.add(specSheetMenu);
        JMenuItem createSpec = new JMenuItem("Create New Spec Sheet");
        JMenuItem editSpec = new JMenuItem("Edit a Spec Sheet");

        createSpec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create a spec sheet
            }
        });

        editSpec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //edit a spec sheet
            }
        });

        specSheetMenu.add(createSpec);
        specSheetMenu.add(editSpec);

    }

    private void makeSearchBar()
    {
        JPanel searchPanel = new JPanel(new GridLayout());
        customerPanel.add(searchPanel, BorderLayout.NORTH);
        JTextField searchBar = new JTextField();
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchBar, 0, 0);
        searchPanel.add(searchButton, 0, 1);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new DBconnector();
                String tempString = searchBar.getText();

                customerDocument = db.findDocument(tempString.split(" ")[0], tempString.split(" ")[1]);
                System.out.println(customerDocument.toString());
                logger.makeLog(customerDocument.toString());
                db.closeConnection();
                fillData();
            }
        });
    }

    private void fillData()
    {
        firstNameField.setText(customerDocument.getString("firstName"));
        lastNameField.setText(customerDocument.getString("lastName"));
    }

    public void make()
    {
        frame.setContentPane(new MainScreen().customerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        MainScreen ms = new MainScreen();
        ms.make();
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
        leftSideway = new JLabel("Left Sideways Pitch");
        labelArrayList.add(leftSideway);
        rightSideway = new JLabel("Right Sideway Pitch");
        labelArrayList.add(rightSideway);
        bridge = new JLabel("Bridge");
        labelArrayList.add(bridge);
        span = new JLabel("Span");
        labelArrayList.add(span);
        cutToCutSpan = new JLabel("Cut to Cut Span");
        labelArrayList.add(cutToCutSpan);
        thumb = new JLabel("Thumb");
        labelArrayList.add(thumb);
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
    }

}
