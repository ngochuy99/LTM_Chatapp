package View;

import javax.swing.*;

public class Client {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton OKButton;
    private JTextField textField4;
    private javax.swing.JPanel JPanel;
    private JButton sendButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

