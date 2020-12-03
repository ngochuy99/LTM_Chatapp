package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        while(true) {
            Socket client = new Socket("localhost",8888);
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            Scanner input = new Scanner(System.in);
            Boardcast(input,dos);
        }
    }
    private static void Boardcast(Scanner input, DataOutputStream dos) throws IOException {
        System.out.println("Message> ");
        String message = input.nextLine();
        dos.writeUTF(message);
    }
}
