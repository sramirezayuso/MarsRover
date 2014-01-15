package rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import rover.MarsRover.Rotation;

public class RoverFileMain {
	
	public static void main(String[] args){
		processFile();
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
				
				System.console().writer().println("Rover starts at " + rv.getHumanReadablePosition() + ".");
				Terrain tr = new Terrain(new CartesianBounds(width-1, height-1));
				if(!tr.addRover(rv))
					return false;
							
				String moves = sc.next();
				processMoves(rv, moves);
				
				System.console().writer().println("Rover ends at " + rv.getHumanReadablePosition() + ".");
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(sc != null)
				sc.close();
		}
		return true;
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
	
	public static void processMoves(MarsRover rv, String moves){
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L'){
				rv.turn(Rotation.L);
				System.console().writer().println("Rover turns left.");
			}
			else if(moves.charAt(i) == 'R'){
				rv.turn(Rotation.R);
				System.console().writer().println("Rover turns right.");
			}
			else if(moves.charAt(i) == 'M'){
				rv.move();
				System.console().writer().println("Rover advances to " + rv.getHumanReadablePosition() +".");
			}
			else if(moves.charAt(i) == 'S')
				rv.shootLasers();
		}
	}
}
