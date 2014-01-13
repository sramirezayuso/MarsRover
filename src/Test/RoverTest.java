package Test;

import junit.framework.TestCase;

import org.junit.Test;

import Main.CartesianBounds;
import Main.CartesianCoordinates;
import Main.CartesianDirection;
import Main.MarsRover;
import Main.PolarBounds;
import Main.PolarCoordinates;
import Main.PolarDirection;
import Main.RoverMain;
import Main.RoverPosition;
import Main.Terrain;

public class RoverTest extends TestCase {

	@Test
	public void testRotation(){
		System.out.println("Test correct rotation...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "LLRLRRL");
		assertEquals(CartesianDirection.W, rv.getPos().getDir());
	}
	
	@Test
	public void testDisplacement(){
		System.out.println("Test correct displacement...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "MM");
		assertEquals(new CartesianCoordinates(1,4), rv.getPos().getCoords());
	}
	
	@Test
	public void testMovement(){
		System.out.println("Test full movement...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "LMLMLMLMM");
		assertEquals(new CartesianCoordinates(1,3), rv.getPos().getCoords());
		assertEquals(CartesianDirection.N, rv.getPos().getDir());
	}
	
	@Test
	public void testBoundsChecking(){
		System.out.println("Test that the rover doesn't go out of bounds...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "MMMMMMMMMMM");
		assertEquals(new CartesianCoordinates(1,4), rv.getPos().getCoords());
	}
	
	@Test
	public void testCollision(){
		System.out.println("Test that the rover doesn't go occupy the same tile as other rover...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		MarsRover rv2 = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 3), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		tr.addRover(rv2);
		RoverMain.processMoves(rv, "LMLMLMLMMMM");
		assertEquals(new CartesianCoordinates(1,2), rv.getPos().getCoords());
	}
	
	@Test
	public void testPolarMovement(){
		System.out.println("Test that the rover moves correctly using polar coordinates...");
		MarsRover rv = new MarsRover(new RoverPosition(new PolarCoordinates(Math.sqrt(Math.pow(1, 2) + Math.pow(2, 2)) , Math.atan2(2, 1)), PolarDirection.N));
		Terrain tr = new Terrain(new PolarBounds(5));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "LMLMLMLMM");
		assertEquals(new PolarCoordinates(3.1622776601683795, 1.2490457723982544), rv.getPos().getCoords());
	}
}
