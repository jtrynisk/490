package GUI;

import WorkOrder.WorkOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWorkOrder {
    private JFrame workFrame;
    private JPanel workPanel;
    private JTextField nameField;
    private JTextField ballField;
    private JTextField emailField;
    private JTextArea notes;
    private JButton clearButton;
    private JButton submitButton;

    public NewWorkOrder() {
        workFrame = new JFrame("Work Order");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                ballField.setText("");
                emailField.setText("");
                notes.setText("");

            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkOrder workOrder = new WorkOrder(nameField.getText().split(" ")[0], nameField.getText().split(" ")[1], ballField.getText(),
                        emailField.getText(), notes.getText());
                workOrder.sendToDb();
                workFrame.dispose();
            }
        });
    }

    public void make()
    {
        workFrame.setContentPane(workPanel);
        workFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        workFrame.pack();
        workFrame.setVisible(true);
    }
}
