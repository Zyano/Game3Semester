package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static ServerSocket serverSocket;

	public static void main(String[] args){

		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("Server started, ready to receive connections");
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Connection accepted");
				
				
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				//Used for writing information to the header for the ObjectInputStream
				output.flush();
				String message = "Hej Stefan!";

				
				output.writeObject(message);
				
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				

			}

		} catch (IOException e) {
			System.err.println("Not possible to crete serversocket");
		}

	}
}
