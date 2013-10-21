package serverTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSendingTest {
	
	private static Socket clientSocket;
	
	
	public static void main(String[] args){
		try {
			clientSocket = new Socket(InetAddress.getLocalHost(), 8888);
			
			
			
			ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
			//Used for writing information to the header for the ObjectInputStream
			output.flush();
			String sendMessage = "Hej Stefan!";
			
			
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			
			//Read from the TCP pipeline
			Object receive = in.readObject();
			//check if the received is a string
			if(receive instanceof String){
				String message = (String) receive;
				System.out.println(message);
			}

			//Send new information out
			output.writeObject(sendMessage);
			output.flush();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
