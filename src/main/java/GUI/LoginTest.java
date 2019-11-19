package GUI;

import Logger.MyLogger;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoginTest {
    private JFrame loginframe;
    private JPanel loginPanel;
    private JTextField userNameField;
    private JLabel userNameLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton clearButton;
    private JButton submitButton1;
    private String username;
    private char[] password;
    private Properties config;
    private MyLogger logger = new MyLogger();


    public LoginTest() {

        loginframe = new JFrame("Please Login");
        config = new Properties();
        try{
            FileReader fr = new FileReader("/Users/jondntryniski/490/src/main/resources/application.conf");
            config.load(fr);
        }catch(IOException e)
        {
            logger.makeLog("Couldn't load the properties file");
        }

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNameField.setText("");
                passwordField.setText("");
            }
        });


        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkUserName(userNameField.getText()) && checkPassword(passwordField.getPassword()))
                {
                    logger.makeLog("Succesful Login");
                    closeWindow();
                    loginframe.dispose();
                    MainScreen ms = new MainScreen();
                    ms.make();
                }
                else {
                    logger.makeLog("Unsuccesful login");
                    FailedLogin fl = new FailedLogin();
                    fl.run();
                    userNameField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }

    private boolean checkUserName(String username)
    {
        if(username.equals(config.getProperty("username")))
            return true;
        return false;
    }

    private boolean checkPassword(char[] password)
    {
        if(new String(password).equals(config.getProperty("password")))
            return true;
        return false;
    }

    private void closeWindow()
    {
        loginframe.setVisible(false);
        loginframe.dispose();
    }

    public void make()
    {
        loginframe.setContentPane(new LoginTest().loginPanel);
        loginframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        loginframe.pack();
        loginframe.setVisible(true);
    }
}
