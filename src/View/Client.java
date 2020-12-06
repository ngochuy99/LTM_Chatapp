package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.Client.Read;
import Controller.Client.Write;
import Model.Message;

public class Client {
    //Swing Var
    private JTextField username;
    private JTextField IP;
    private JTextField Port;
    private JButton OKButton;
    private JTextField Input;
    private javax.swing.JPanel JPanel;
    private JButton sendButton;
    private JTextArea Message;
    private JButton Exit;
    //Socket Var
    private String socket_Username;
    private String socket_IP;
    private String socket_Port;
    private Read readThread;
    private Write writeThread;
    private Socket socket;

    public Client() {
        //Connect to room
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                socket_IP = IP.getText();
                socket_Port = Port.getText();
                socket_Username = username.getText();
                changeStatus(false);
                try {
                    init();
                    JOptionPane.showMessageDialog(JPanel,"Connect to server successfully");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(JPanel,"Connect to server failed, Please check IP and port");
                    ioException.printStackTrace();
                }
            }
        });
        //Send new message to room by pressing enter key
        Input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        Date dateNow = new Date();
                        Model.Message message = new Message(Input.getText(),dateNow);
                        sendMessage(message);
                        Input.setText("");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        //Send new message to room
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date dateNow = new Date();
                    Model.Message message = new Message(Input.getText(),dateNow);
                    sendMessage(message);
                    Input.setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        //Disconnect From chatroom
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writeThread.sendMessage(new Message("Exit",new Date()));
                    socket_Username = "";
                    socket_Port = "";
                    socket_IP = "";
                    IP.setText("");
                    Port.setText("");
                    Message.setText("");
                    writeThread.interrupt();
                    readThread.interrupt();
                    changeStatus(true);
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public void init() throws IOException {
        socket = new Socket(socket_IP, Integer.parseInt(socket_Port));
        writeThread = new Write(socket,this);
        readThread = new Read(socket,this);
        writeThread.start();
        readThread.start();
    }
    public String getUsername(){
        return this.socket_Username;
    }
    public void appendNewMessage(String incommingMessage){
        Message.append(incommingMessage+"\n");
    }
    public void sendMessage(String message) throws IOException {
        writeThread.sendMessage(message);
    }
    public void appendNewMessage(Message incommingMessage){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String mess = incommingMessage.getMessage() + " ------ "+formatter.format(incommingMessage.getTimeCreated()+"\n");
        Message.append(mess);
    }
    public void sendMessage(Message message) throws IOException {
        writeThread.sendMessage(message);
    }
    public void changeStatus(boolean status){
        IP.setEditable(status);
        Port.setEditable(status);
        username.setEditable(status);
        sendButton.setEnabled(!status);
        Input.setEditable(!status);
    }
}

