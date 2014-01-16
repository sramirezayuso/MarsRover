package rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import rover.notation.CardinalNotation;
import rover.notation.CommandNotation;
import rover.notation.InputNotation;
import rover.notation.LRMSNotation;
import rover.position.cartesian.CartesianBounds;

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
				
				InputNotation in = new CardinalNotation();
				RoverPosition rvPos = in.loadValues(sc);			
				MarsRover rv = new MarsRover(rvPos);
				
				System.console().writer().println("Rover starts at " + rv.getHumanReadablePosition() + ".");
				Terrain tr = new Terrain(new CartesianBounds(width-1, height-1));
				if(!tr.addRover(rv))
					return false;
							
				String moves = sc.next();
				CommandNotation cn = new LRMSNotation();
				cn.processMoves(rv, moves);
				
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
}
