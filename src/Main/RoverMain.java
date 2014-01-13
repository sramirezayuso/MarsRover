package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Main.MarsRover.Rotation;


public class RoverMain {
	public static void main(String[] args){
		TelnetServer server = new TelnetServer();
		server.run();
	}
	
	private static boolean processFile(){
		Scanner sc = null;
		try {
			sc = new Scanner(new File("input.in"));
			int height = sc.nextInt();
			int width = sc.nextInt();
			while(sc.hasNext()){
				
				RoverPosition rvPos = loadCartesianValues(sc);
				
				MarsRover rv = new MarsRover(rvPos);
				Terrain tr = new Terrain(new CartesianBounds(width-1, height-1));
				if(!tr.addRover(rv))
					return false;
							
				String moves = sc.next();
				processMoves(rv, moves);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(sc != null)
				sc.close();
		}
		return true;
	}
	
	public static void processMoves(MarsRover rv, String moves){
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L')
				rv.turn(Rotation.L);
			else if(moves.charAt(i) == 'R')
				rv.turn(Rotation.R);
			else
				rv.move();
		}
	}
	
	private static RoverPosition loadCartesianValues(Scanner sc){
		
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

