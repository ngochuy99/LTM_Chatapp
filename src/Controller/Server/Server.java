package Controller.Server;

import Model.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private int port=8888;
    private ArrayList<UserThread> UserThreads = new ArrayList<UserThread>();
    private ArrayList<String> userList = new ArrayList<String>();
    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.execute();
    }
    public void execute(){
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is running on port " + 8888);
            while(true) {
                Socket socket = server.accept();
                UserThread newUser = new UserThread(socket, this);
                UserThreads.add(newUser);
                newUser.start();
            }
        }
        catch (IOException ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    public void boardcasting(String message){
        for(UserThread user : UserThreads){
            user.sendMessage(message);
        }
    }
    public void boardcasting(Message message){
        for(UserThread user : UserThreads){
            user.sendMessage(message);
        }
    }
    public void removeUser(UserThread user){
        UserThreads.remove(user);
    }
    public void addUsername(String username){
        userList.add(username);
    }
    public void removeUsername(String username){
        userList.remove(username);
    }
}
