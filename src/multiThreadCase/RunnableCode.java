package multiThreadCase;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

// Code for implementing Runnable interface
public class RunnableCode implements Runnable{
	protected Socket socketClient = null;
	protected String serverMsg   = null;

	public RunnableCode(Socket clientSocket, String textFromServer) {
		this.socketClient = clientSocket;
		this.serverMsg   = textFromServer;

	}
	public void run() {
		try {
			InputStream is  = socketClient.getInputStream();
			OutputStream os = socketClient.getOutputStream();
			long time = System.currentTimeMillis();
			os.write(("OK\n\nRunnable: " + this.serverMsg + " - " +time +"").getBytes());
			System.out.println("OK\n\nRunnable: " + this.serverMsg + " - " +Thread.currentThread() +time +"");
			os.close();
			is.close();
			System.out.println("Your request has been processed in time : " + time);
		} 
		catch (IOException e) {           
			e.printStackTrace();
		}
	}
}