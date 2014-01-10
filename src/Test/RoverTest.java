package Test;

import junit.framework.TestCase;

import org.junit.Test;

import Main.Boundaries;
import Main.Coordinates;
import Main.MarsRover;
import Main.RoverMain;
import Main.RoverPosition;
import Main.Terrain;

public class RoverTest extends TestCase {

	@Test
	public void testRotation(){
		System.out.println("Test correct rotation...");
		MarsRover rv = new MarsRover(new RoverPosition(new Coordinates(1, 2), MarsRover.Direction.N) );
		Terrain tr = new Terrain(new Boundaries(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "LLRLRRL");
		assertEquals(MarsRover.Direction.W, rv.getPos().getDir());
	}
	
	@Test
	public void testDisplacement(){
		System.out.println("Test correct displacement...");
		MarsRover rv = new MarsRover(new RoverPosition(new Coordinates(1, 2), MarsRover.Direction.N) );
		Terrain tr = new Terrain(new Boundaries(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "MM");
		assertEquals(new Coordinates(1,4), rv.getPos().getCoords());
	}
	
	@Test
	public void testMovement(){
		System.out.println("Test full movement...");
		MarsRover rv = new MarsRover(new RoverPosition(new Coordinates(1, 2), MarsRover.Direction.N) );
		Terrain tr = new Terrain(new Boundaries(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "LMLMLMLMM");
		assertEquals(new Coordinates(1,3), rv.getPos().getCoords());
		assertEquals(MarsRover.Direction.N, rv.getPos().getDir());
	}
	
	@Test
	public void testBoundsChecking(){
		System.out.println("Test that the rover doesn't go out of bounds...");
		MarsRover rv = new MarsRover(new RoverPosition(new Coordinates(1, 2), MarsRover.Direction.N) );
		Terrain tr = new Terrain(new Boundaries(4, 4));
		tr.addRover(rv);
		RoverMain.processMoves(rv, "MMMMMMMMMMM");
		assertEquals(new Coordinates(1,4), rv.getPos().getCoords());
	}
	
	@Test
	public void testCollision(){
		System.out.println("Test that the rover doesn't go occupy the same tile as other rover...");
		MarsRover rv = new MarsRover(new RoverPosition(new Coordinates(1, 2), MarsRover.Direction.N) );
		MarsRover rv2 = new MarsRover(new RoverPosition(new Coordinates(1, 3), MarsRover.Direction.N) );
		Terrain tr = new Terrain(new Boundaries(4, 4));
		tr.addRover(rv);
		tr.addRover(rv2);
		RoverMain.processMoves(rv, "LMLMLMLMMMM");
		assertEquals(new Coordinates(1,2), rv.getPos().getCoords());
	}
}
