import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WorkerThread implements Runnable {
	
	protected Socket clientSocket;
	protected String text;
    private InputStream is;
    private OutputStream os;
    PrintWriter pw;

    public void setInputStream(InputStream is) {
        this.is = is;
    }
	
	public WorkerThread(Socket clientSocket, String text) {
		this.clientSocket = clientSocket;
		this.text = text;		
	}

    public WorkerThread(InputStream is, OutputStream os, String text) {
        this.is = is;
        this.os = os;
        this.text = text;
    }
	
	public void run()  {
		try {
			Scanner sc = new Scanner(is);
			pw = new PrintWriter(os, true);		  
			boolean done = false;
			
			while (!done) {
				pw.println("Grid size:");
				int height = sc.nextInt();
				int width = sc.nextInt();
				
				pw.println("Rover position:");
				RoverPosition rvPos = loadValues(sc);
				MarsRover rv = new MarsRover(rvPos);
				Terrain tr = new Terrain(new Coordinates(width-1, height-1));
				if(!tr.addRover(rv))
					return;
				rv.addTerrain(tr);
				
				pw.println("Instructions:");
				String moves = sc.next();
				rv.receiveMoves(moves);
			
				pw.println(rv.getPos());

			}
			is.close();
			os.close();			
		}catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			pw.close();
			try{
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private RoverPosition loadValues(Scanner sc){
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		String strDir = sc.next();
		MarsRover.Direction dir = MarsRover.Direction.N;
		if(strDir.equals("N"))
			dir = MarsRover.Direction.N;
		else if(strDir.equals("E"))
			dir = MarsRover.Direction.E;
		else if(strDir.equals("S"))
			dir = MarsRover.Direction.S;
		else if(strDir.equals("W"))
			dir = MarsRover.Direction.W;
		
		return new RoverPosition(new Coordinates(x, y), dir);
	}
}