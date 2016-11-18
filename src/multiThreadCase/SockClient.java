package multiThreadCase;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SockClient {
	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println(
					"Usage: EchoClient Java <host_name> <port_number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNo = Integer.parseInt(args[1]);

		//try-catch block starts from here

		try {
			Socket s = new Socket(hostName, portNo);
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));

			String input;
			while ((input = bRead.readLine()) != null) {
				pw.println(input);

				String serverResponse = bufRead.readLine();
				System.out.println("echo: " + serverResponse);
				if(serverResponse.equalsIgnoreCase("Client connection is closed")){
					System.out.println("Client loop will be broken and it can exit");                	
					break;
				}
			}
			System.out.println("Client process is Terminated");
		} 
		catch (UnknownHostException e) {
			System.err.println("Error with the host " + hostName);
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("I/O exception occured for the connection" + hostName);
			System.exit(1);
		} 
	}
}