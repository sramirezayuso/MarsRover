package rover;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import rover.notation.CardinalNotation;
import rover.notation.CommandNotation;
import rover.notation.InputNotation;
import rover.notation.LRMSNotation;
import rover.position.cartesian.CartesianBounds;
import rover.position.cartesian.CartesianCoordinates;

class WorkerThread implements Runnable {
	
    private InputStream is;
    private OutputStream os;
    private PrintWriter pw;
    private static Terrain tr = new Terrain(new CartesianBounds(5, 5));
    

    void setInputStream(InputStream is) {
        this.is = is;
    }

    WorkerThread(InputStream is, OutputStream os, String text) {
        this.is = is;
        this.os = os;
    }
	
	public void run()  {
		Scanner sc = new Scanner(is);
		pw = new PrintWriter(os, true);
		boolean exit = false;
		while(!exit){
			pw.println("Please select an option:\nCreate private Mars - 1\nEnter public Mars - 2\nExit - 3");
			String cmd = sc.next();
			if(cmd.equals("1")){
				pw.println("Terrain size:");
				int height = sc.nextInt();
				int width = sc.nextInt();
				readInstructions(sc, pw, new Terrain(new CartesianBounds(width-1, height-1)));
				exit = true;
			}
			else if(cmd.equals("2")){
				readInstructions(sc, pw, tr);
				exit = true;
			}
			else if(cmd.equals("3"))
				exit = true;
			else
				pw.println("Invalid command.");
		}
		
		pw.close();
		
		try {
			if(is != null)
				is.close();
			if(os != null)
				os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	private void readInstructions(Scanner sc, PrintWriter pw, Terrain tr){
		boolean exit = false;
		while(!exit){
			pw.println("Please select an option:\nAdd rover - 1\nMove rover - 2\nExit - 3");
			String cmd = sc.next();
			if(cmd.equals("1")){
				pw.println("Enter new rover position:");
				InputNotation in = new CardinalNotation();
				RoverPosition rvPos = in.loadValues(sc);
				MarsRover rv = new MarsRover(rvPos);
				if(!tr.addRover(rv))
					pw.println("That position is occupied or out of bounds.");
			}
			else if(cmd.equals("2")){
				pw.println("Enter coordinates of the rover you want to move:");
				int x = sc.nextInt();
				int y = sc.nextInt();
				MarsRover rv = tr.getRoverAt(new CartesianCoordinates(x, y));
				if(rv == null)
					pw.println("There's no rover at that location.");
				else {
					pw.println("Enter instructions:");
					String moves = sc.next();
					CommandNotation cn = new LRMSNotation();
					cn.processMoves(rv, moves);
					pw.println("Mars rover final location:" + rv.getHumanReadablePosition());
				}
			}
			else if(cmd.equals("3"))
				exit = true;
			else
				pw.println("Invalid command.");
		}
	}
}