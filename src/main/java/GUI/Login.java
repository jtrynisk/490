package GUI;

import Logger.MyLogger;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private boolean loggedIn;
    private Properties config;
    private MyLogger logger = new MyLogger();

    public Login() {
        config = new Properties();
        try{
            FileReader fr = new FileReader("/Users/jondntryniski/490/src/main/resources/application.conf");
            config.load(fr);
        }catch(IOException e)
        {
            logger.makeLog("Couldn't load the properties file");
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if(checkUserName(usernameField.getText()) && checkPassword(passwordField1.getPassword()))
        {
            logger.makeLog("Succesful Login");
            loggedIn = true;
            MainScreen ms = new MainScreen();
            ms.make();
        }
        else {
            logger.makeLog("Unsuccesful login");
            FailedLogin fl = new FailedLogin();
            fl.run();
            usernameField.setText("");
            passwordField1.setText("");
        }
        dispose();
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

    private void onCancel() {
        usernameField.setText("");
        passwordField1.setText("");
    }

}
