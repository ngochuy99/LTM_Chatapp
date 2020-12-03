package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Write extends Thread{
    private Socket socket;
    private Client client;

    public Write(Socket socket,Client client){
        this.client = client;
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            dos.writeUTF(client.getUsername());
            while(true){
                String message = input.nextLine();
                dos.writeUTF(message);
                if(message.equals("Exit")){
                    client.closeConnection();
                    break;
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
