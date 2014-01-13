package Main;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import Main.MarsRover.Rotation;

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
				RoverPosition rvPos = loadCartesianValues(sc);
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
					processMoves(rv, moves);
					pw.println("Mars rover final location:" + rv.getHumanReadablePosition());
				}
			}
			else if(cmd.equals("3"))
				exit = true;
			else
				pw.println("Invalid command.");
		}
	}
	
	private void processMoves(MarsRover rv, String moves){
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L')
				rv.turn(Rotation.L);
			else if(moves.charAt(i) == 'R')
				rv.turn(Rotation.R);
			else
				if(!rv.move()){
					pw.println("The mars rover has ran into another rover or the wall!");
					return;
				}
		}
	}
	
	private RoverPosition loadCartesianValues(Scanner sc){
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		String strDir = sc.next();
		CartesianDirection dir = CartesianDirection.N;
		if(strDir.equals("N"))
			dir = CartesianDirection.N;
		else if(strDir.equals("E"))
			dir = CartesianDirection.E;
		else if(strDir.equals("S"))
			dir = CartesianDirection.S;
		else if(strDir.equals("W"))
			dir = CartesianDirection.W;
		
		return new RoverPosition(new CartesianCoordinates(x, y), dir);
	}
}