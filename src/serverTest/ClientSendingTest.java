package serverTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSendingTest {
	
	private static Socket clientSocket;
	
	
	public static void main(String[] args){
		try {
			clientSocket = new Socket(InetAddress.getLocalHost(), 8888);
			
			
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			
			Object receive;
			while((receive = in.readObject()) != null){
				String message = (String) receive;
				System.out.println(message);
			}
			
			
			
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
