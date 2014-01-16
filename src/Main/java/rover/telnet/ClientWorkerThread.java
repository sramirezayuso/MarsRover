package rover.telnet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import rover.RoverPosition;

public class ClientWorkerThread implements Runnable{
	private int port = 5072;
	private	Socket sckt = null;
	private RoverPosition startPos;
	private String moves;
	private PrintWriter pw;
	
	public ClientWorkerThread(RoverPosition startPos, String moves){
		this.startPos = startPos;
		this.moves = moves;
	}
	
	public void run(){
		openClientSocket();
		try {
			pw = new PrintWriter(sckt.getOutputStream(), true);
		} catch(IOException e) {
			System.out.println("Error while trying to write to socket.");
		}
		pw.println("2");
		pw.println("1");
		pw.println("" + startPos.getCoords().machineString() + " " + startPos.getDir());
		pw.println("2");
		pw.println("" + startPos.getCoords().machineString());
		pw.println(moves);
		pw.println("3");
		try {
			sckt.close();
		} catch(IOException e) {
			System.out.println("Error closing socket.");
		}
		return;

	}
	
    private void openClientSocket()
    {
 	   try{
 		   this.sckt = new Socket("127.0.0.1", 5072);
 	   }catch(IOException ex) {
 		  throw new RuntimeException("Can't open connection on port "+ port);  
 	   }
    }
	
}
