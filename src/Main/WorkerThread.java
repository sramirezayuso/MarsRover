package Main;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import Main.MarsRover.Rotation;

public class WorkerThread implements Runnable {
	
    private InputStream is;
    private OutputStream os;
    PrintWriter pw;

    public void setInputStream(InputStream is) {
        this.is = is;
    }

    public WorkerThread(InputStream is, OutputStream os, String text) {
        this.is = is;
        this.os = os;
    }
	
	public void run()  {
		Scanner sc = new Scanner(is);
		pw = new PrintWriter(os, true);
		
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
		processMoves(rv, moves);
	
		pw.println(rv.getPos());
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
	
	private static void processMoves(MarsRover rv, String moves){
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L')
				rv.turn(Rotation.L);
			else if(moves.charAt(i) == 'R')
				rv.turn(Rotation.R);
			else
				rv.move();
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