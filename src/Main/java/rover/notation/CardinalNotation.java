package rover.notation;

import java.util.Scanner;

import rover.RoverPosition;
import rover.position.cartesian.CartesianCoordinates;
import rover.position.cartesian.CartesianDirection;

public class CardinalNotation implements InputNotation{
	
	@Override
	public RoverPosition loadValues(Scanner sc){
		
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
