package GUI;


import sun.rmi.runtime.Log;

public class Main{

    public static void main(String[] args) {
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);
    }
}
