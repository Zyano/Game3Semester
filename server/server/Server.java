package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import serverService.ServerService;

public class Server {
	
	private static ServerSocket serverSocket;

	public static void main(String[] args){

		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("Server started, ready to receive connections");
			ServerService service = ServerService.getInstance();
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Connection accepted");
				InetAddress ip = socket.getInetAddress();
				
				//Creating the ObjectOutputStream first, and thereby flushing so
				//the head information is set before creating ObjectInputStream
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.flush();
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				
				//For each connection we start an in and an out thread,
				//sending the ip in both, thereby making it possible for 
				//adding updates and removing different players.
				System.out.println("Now starting threads for connection");
				(new ObjectInThread(input, ip, service)).start();
				ObjectOut outStream = new ObjectOut(output, ip);
				//Setting the sendExecuter
				service.addSendExecuter(outStream);
				
			}

		} catch (IOException e) {
			System.err.println("Not possible to create serversocket");
		}
	}
}
