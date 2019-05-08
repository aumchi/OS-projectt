import java.net.*;
import java.io.*;
import java.util.*;

public class HangmanServer {
    public static void main(String[] args) {
        ServerSocket welcomeSocket = null;
        try {
            welcomeSocket = new ServerSocket(1667);
            System.out.println("waiting for client connect at port number 1667");
        } catch (IOException e) {
            System.out.println("Cannot create a welcome socket");
            System.exit(1);
        }
        while (true) {
            try {
                Scanner sc =new Scanner(System.in);
                Socket connectionSocket = welcomeSocket.accept();
               // System.out.println("Waiting for client connect at port number 1667 ");
                DataOutputStream data = new DataOutputStream(connectionSocket.getOutputStream());
                data.writeUTF("Guess any letter in the word");
                // System.out.println("enter movie name");
                // String movie = sc.nextLine();
              //  data.writeUTF("aum");
                HangmanThread h = new HangmanThread(connectionSocket);
                h.start();

            } catch (Exception e) {
            }
        }
    }
}