package Controller.Server;

import Model.Message;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class UserThread extends Thread {
    private Socket socket ;
    private Server server;
    private Message username;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public UserThread(Socket socket,Server server){
        this.socket = socket;
        this.server = server;
    }
    @Override
    public void run(){
        try{
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            username =( Message) ois.readObject();
            server.addUsername(username.getMessage());
            server.boardcasting(new Message(username.getMessage()+" has joined the room",new Date()));
            while(true){
                Message messsageFromClient = (Message) ois.readObject();
                if(messsageFromClient.getMessage().equals("Exit")){
                    System.out.println("run here");
                    server.removeUser(this);
                    server.removeUsername(username.getMessage());
                    messsageFromClient.setMessage(username.getMessage()+" has left the room");
                    server.boardcasting(messsageFromClient);
                    socket.close();
                    break;
                }
                messsageFromClient.setMessage(username.getMessage()+": "+messsageFromClient.getMessage());
                server.boardcasting(messsageFromClient);
            }
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("Error" +ex);
            ex.printStackTrace();
        }
    }
    public void sendMessage(String message){
        try{
            dos.writeUTF(message);
        }
        catch (IOException ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    public void sendMessage(Message message){
        try{
            oos.writeObject(message);
        }
        catch (IOException ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}
