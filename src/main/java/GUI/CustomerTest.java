package GUI;

import Customer.Customer;
import Database.DBconnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerTest {
    private JFrame customerFrame;
    private JPanel CustomerPanel;
    private JTextField LastNameField;
    private JTextField FirstNameField;
    private JLabel LastNameLabel;
    private JLabel FirstNameLabel;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JTextField emailField;
    private JLabel emailLabel;
    private JTextField streetField;
    private JLabel streetLabel;
    private JTextField cityField;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JComboBox stateCombo;
    private JButton submitButton;
    private JButton clearButton;
    private JTextField stateField;

    public CustomerTest() {
        customerFrame = new JFrame("Create a new customer");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = new Customer(FirstNameField.getText(), LastNameField.getText(), emailField.getText(), streetField.getText(), cityField.getText(),
                                                stateField.getText(), phoneField.getText());
                DBconnector db = new DBconnector();
                db.writeDocument(customer.createDocument());
                db.closeConnection();
                customerFrame.dispose();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstNameField.setText("");
                LastNameField.setText("");
                emailField.setText("");
                streetField.setText("");
                cityField.setText("");
                stateField.setText("");
                phoneField.setText("");
            }
        });
    }

    public void make()
    {
        customerFrame.setContentPane(CustomerPanel);
        customerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        customerFrame.pack();
        customerFrame.setVisible(true);
    }
}
