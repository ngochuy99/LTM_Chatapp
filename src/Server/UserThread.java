package Server;

import Server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserThread extends Thread {
    private Socket socket ;
    private Server server;
    private String username;
    private DataOutputStream dos;
    private DataInputStream dis;

    public UserThread(Socket socket,Server server){
        this.socket = socket;
        this.server = server;
    }
    @Override
    public void run(){
        try{
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            username = dis.readUTF();
            server.addUsername(username);
            server.boardcasting(username+" has joined the room");
            while(true){
                String messsageFromClient = dis.readUTF();
                if(messsageFromClient.equals("Exit")){
                    server.removeUser(this);
                    server.removeUsername(username);
                    socket.close();
                    server.boardcasting(username + "has left the room");
                    break;
                }
                String returnMessage = "["+username+"] :" + messsageFromClient;
                server.boardcasting(returnMessage);
            }
        }
        catch (IOException ex){
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
}
