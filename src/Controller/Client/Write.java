package Controller.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import Model.Message;
import View.*;
import View.Client;

import javax.xml.crypto.Data;

public class Write extends Thread{
    private Socket socket;
    private Client client;
    private ObjectOutputStream oos;
    public Write(Socket socket, Client client){
        this.client = client;
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            Date dateNow = new Date();
            oos.writeObject(new Message(client.getUsername(),dateNow));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void sendMessage(String message) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(message);
    }
    public void sendMessage(Message message) throws IOException {
        oos.writeObject(message);
    }
}
