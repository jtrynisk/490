package GUI;

import Customer.SpecSheet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSpec {
    private JFrame specFrame;
    private JPanel specPanel;
    private JTextField handField;
    private JTextField OvalField;
    private JTextField leftFingerField;
    private JTextField rightFingerField;
    private JTextField leftReverseField;
    private JTextField rightReverseField;
    private JTextField leftforwardPitchField;
    private JTextField rightForwardPitchfField;
    private JTextField leftLateralPitchField;
    private JTextField rightLateralPitchField;
    private JTextField bridgeField;
    private JTextField spanField;
    private JTextField cutToCutField;
    private JTextField thumbField;
    private JTextField thumbTypeField;
    private JTextField thumbForwardField;
    private JTextField thumbReverseField;
    private JTextField thumbLeftField;
    private JTextField thumbRightField;
    private JTextField degreeField;
    private JTextField widthField;
    private JTextField gripField;
    private JButton clearButton;
    private JButton submitButton;
    private JTextField nameField;

    public CreateSpec() {
        specFrame = new JFrame("Spec Sheet Creation");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handField.setText("");
                OvalField.setText("");
                leftFingerField.setText("");
                rightFingerField.setText("");
                leftReverseField.setText("");
                rightReverseField.setText("");
                leftforwardPitchField.setText("");
                rightForwardPitchfField.setText("");
                leftLateralPitchField.setText("");
                rightLateralPitchField.setText("");
                bridgeField.setText("");
                spanField.setText("");
                cutToCutField.setText("");
                thumbField.setText("");
                thumbTypeField.setText("");
                thumbForwardField.setText("");
                thumbReverseField.setText("");
                thumbLeftField.setText("");
                thumbRightField.setText("");
                degreeField.setText("");
                widthField.setText("");
                gripField.setText("");
                nameField.setText("");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpecSheet ss = new SpecSheet(handField.getText(), OvalField.getText(), leftFingerField.getText(), rightFingerField.getText(), leftReverseField.getText(),
                        rightReverseField.getText(), leftforwardPitchField.getText(), rightForwardPitchfField.getText(), leftLateralPitchField.getText(),
                        rightLateralPitchField.getText(), bridgeField.getText(), spanField.getText(), cutToCutField.getText(), thumbField.getText(), thumbTypeField.getText(),
                        thumbForwardField.getText(), thumbReverseField.getText(), thumbLeftField.getText(), thumbRightField.getText(), degreeField.getText(), widthField.getText(),
                        gripField.getText());

                ss.createDocument(nameField.getText().split(" ")[0], nameField.getText().split(" ")[1]);
                specFrame.dispose();
            }
        });
    }

    public void make()
    {
        specFrame.setContentPane(specPanel);
        specFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        specFrame.pack();
        specFrame.setVisible(true);
    }
}
