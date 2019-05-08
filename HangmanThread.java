import java.io.*;
import java.net.*;
import java.util.*;

public class HangmanThread extends Thread {

    private Socket connectionSocket;
    private static String[] words = {"terminator", "banana", "computer", "cow", "rain", "water" };
	private static String word = words[(int) (Math.random() * words.length)];
	private static String asterisk = new String(new char[word.length()]).replace("\0", "#");
    private static int count = 0;
   // static String asterisk = null;


    public HangmanThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public void run() {
        Scanner inFromClient = null;
        DataOutputStream outToClient = null;
        try {
            System.out.println("welcome to socket");
            inFromClient = new Scanner(connectionSocket.getInputStream());
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String guess = inFromClient.nextLine();
            
            

            while (count < 7 && asterisk.contains("#")) {
                System.out.println("Guess any letter in the word");
                System.out.println(asterisk);
                guess = inFromClient.next();
                hang(guess);
                outToClient.writeUTF(String.valueOf(count));
                outToClient.writeUTF(asterisk);

            }
            

        } catch (IOException e) {
            System.err.println("Closing Socket connection");
        } finally {
            try {
                if (inFromClient != null) {
                    inFromClient.close();
                }
                if (outToClient != null) {
                    outToClient.close();
                }
                if (connectionSocket != null) {
                    connectionSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void hang(String guess) {
		String newasterisk = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess.charAt(0)) {
				newasterisk += guess.charAt(0);
			} else if (asterisk.charAt(i) != '#') {
				newasterisk += word.charAt(i);
			} else {
				newasterisk += "#";
			}
		}
	
		if (asterisk.equals(newasterisk)) {
            count++;
            System.out.println("Wrong guess, try again");
		//	hangmanImage();
		} else {
			asterisk = newasterisk;
		}
		if (asterisk.equals(word)) {
			System.out.println("Correct! You win! The word was " + word);
		}
	}
	
	public static void hangmanImage() {
		if (count == 1) {
			System.out.println("Wrong guess, try again");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("___|___");
			System.out.println();
		}
		if (count == 2) {
			System.out.println("Wrong guess, try again");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 3) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   | ");
			System.out.println("___|___");
		}
		if (count == 4) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 5) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 6) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
		if (count == 7) {
			System.out.println("GAME OVER!");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |          _|_");
			System.out.println("   |         / | \\");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
			System.out.println("GAME OVER! The word was " + word);
		}
	}
}
