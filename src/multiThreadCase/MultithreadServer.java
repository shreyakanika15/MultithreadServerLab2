package multiThreadCase;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

// code for implementing Runnable interface for multithreaded server
public class MultithreadServer implements Runnable{
	protected int          portVal   = 8080;
	protected ServerSocket socketServerVal = null;
	protected boolean      hasStopped    = false;
	protected Thread       thread1= null;

	public MultithreadServer(int port){
		this.portVal = port;
	}

	public void run(){
		synchronized(this){
			this.thread1 = Thread.currentThread();
			System.out.println("Thread running" +this.thread1);
		}
		socketServer();
		while(! hasStopped()){
			Socket clientSocket = null;
			try {


				clientSocket = this.socketServerVal.accept();
				new Thread(
						new RunnableCode(
								clientSocket, "This is a multithreaded Server")
						).start();

			} 
			catch (IOException e) {
				if(hasStopped()) {

					System.out.println("Server Stopped...Please check for exception message") ;
					e.printStackTrace();
					return;
				}
				throw new RuntimeException(
						"Client cannot be connected due to some error", e);
			}

		}
		System.out.println("Server Stopped...Please check for the issue") ;
	}
	private synchronized boolean hasStopped() {
		return this.hasStopped;
	}
	public synchronized void closeServer(){
		this.hasStopped = true;
		try {
			this.socketServerVal.close();
		} catch (IOException e) {
			throw new RuntimeException("Server can not be closed - Please check error", e);
		}
	}
	private void socketServer() {
		try {
			this.socketServerVal = new ServerSocket(this.portVal);
		} catch (IOException e) {
			throw new RuntimeException("Problem in opening Port 8080", e);
		}
	}

}