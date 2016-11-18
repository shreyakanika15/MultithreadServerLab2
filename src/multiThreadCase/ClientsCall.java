package multiThreadCase;

import java.io.IOException;

public class ClientsCall{
	public static void main(String [] agrs) throws IOException{
		MultithreadServer server = new MultithreadServer(8080);
		new Thread(server).start();

		try {
			Thread.sleep(20 * 10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Stopping the new Server which was started");
		server.closeServer();
	}
}