package Client;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Read extends Thread{
    private Socket socket;
    private Client client;

    public Read (Socket socket,Client client){
        this.client = client;
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true){
                System.out.println(dis.readUTF());
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
