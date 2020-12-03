package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(8888);
        ArrayList <Socket> clientList = new ArrayList<Socket>();
        System.out.println("Chat server is running on port 8888");
        while(true){
            //Receive connect from client
            Socket connectionSocket = server.accept();
            System.out.println("New User connected");
            if(!clientList.contains(connectionSocket)){
                clientList.add(connectionSocket);
            }
        }
    }
}
