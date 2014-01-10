package Main;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelnetServer implements Runnable{
	private int port = 5072;
	private	ServerSocket sckt = null;
	private boolean isStopped = false;
	private Thread runningThread = null;
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
	
	public void run() {
		synchronized(this) {
			this.runningThread = Thread.currentThread();
		}
		
		openServerSocket();
		
		while(!isStopped) {
			Socket client = null;
			try {
				client = this.sckt.accept();
				this.threadPool.execute(new WorkerThread(client.getInputStream(), client.getOutputStream(), "Thread for each server"));
			} catch (IOException e) {
				throw new RuntimeException("Error while accepting connection");
			}
		}
		
		this.threadPool.shutdown();
		stop();
	}
	
    private void stop() {
 	   this.isStopped = true;
 	   try {
 		   this.sckt.close();
 	   }catch(IOException ex) {
 		   throw new RuntimeException("Error while closing server socket.");
 	   }
    }
    
    private void openServerSocket()
    {
 	   try{
 		   this.sckt = new ServerSocket(this.port);
 	   }catch(IOException ex) {
 		  throw new RuntimeException("Can't open connection on port "+ port);  
 	   }
    }
}