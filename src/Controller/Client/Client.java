package Controller.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String username;
    private Socket socket;
    private Read readThread;
    private Write writeThread;
    public static void main(String[] args) throws Exception{
        Client client = new Client();
        client.execute();
    }
    public void execute() throws IOException{
        try {
            socket = new Socket("localhost", 8888);
            System.out.println("Connected to server");
            Scanner input = new Scanner(System.in);
            System.out.print("What is your name? ");
            username = input.nextLine();
            //Thread to wait for write message to server

            //Thread to wait for message from server

            readThread.start();
            writeThread.start();

        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void closeConnection(){
        try {
            this.readThread.interrupt();
            this.writeThread.interrupt();
            this.socket.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
