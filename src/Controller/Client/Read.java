package Controller.Client;


import Model.Message;
import View.Client;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class Read extends Thread{
    private Socket socket;
    private Client client;

    public Read (Socket socket, View.Client client){
        this.client = client;
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true){
                Message incomingMessage = (Message) ois.readObject();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String mess = incomingMessage.getMessage() + " ------ "+ formatter.format(incomingMessage.getTimeCreated()) +"+\n";
                client.appendNewMessage(mess);
            }
        }
        catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
