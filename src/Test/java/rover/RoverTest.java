package rover;

import junit.framework.TestCase;

import org.junit.Test;

public class RoverTest extends TestCase {

	@Test
	public void testRotation(){
		System.out.println("Test correct rotation...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverTelnetMain.processMoves(rv, "LLRLRRL");
		assertEquals(CartesianDirection.W, rv.getPos().getDir());
	}
	
	@Test
	public void testDisplacement(){
		System.out.println("Test correct displacement...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverTelnetMain.processMoves(rv, "MM");
		assertEquals(new CartesianCoordinates(1,4), rv.getPos().getCoords());
	}
	
	@Test
	public void testMovement(){
		System.out.println("Test full movement...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverTelnetMain.processMoves(rv, "LMLMLMLMM");
		assertEquals(new CartesianCoordinates(1,3), rv.getPos().getCoords());
		assertEquals(CartesianDirection.N, rv.getPos().getDir());
	}
	
	@Test
	public void testBoundsChecking(){
		System.out.println("Test that the rover doesn't go out of bounds...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(4, 4));
		tr.addRover(rv);
		RoverTelnetMain.processMoves(rv, "MMMMMMMMMMM");
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
		RoverTelnetMain.processMoves(rv, "LMLMLMLMMMM");
		assertEquals(new CartesianCoordinates(1,2), rv.getPos().getCoords());
	}
	
	@Test
	public void testPolarMovement(){
		System.out.println("Test that the rover moves correctly using polar coordinates...");
		MarsRover rv = new MarsRover(new RoverPosition(new PolarCoordinates(Math.sqrt(Math.pow(1, 2) + Math.pow(2, 2)) , Math.atan2(2, 1)), PolarDirection.N));
		Terrain tr = new Terrain(new PolarBounds(5));
		tr.addRover(rv);
		RoverTelnetMain.processMoves(rv, "LLMLLMLLMLLMM");
		assertEquals(new PolarCoordinates(3.1622776601683795, 1.2490457723982544), rv.getPos().getCoords());
	}
	
	@Test
	public void testShotOnTarget(){
		System.out.println("Test shooting and hitting rover...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N) );
		MarsRover rv2 = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 3), CartesianDirection.N) );
		Terrain tr = new Terrain(new CartesianBounds(5, 5));
		tr.addRover(rv);
		tr.addRover(rv2);
		RoverTelnetMain.processMoves(rv, "LMLMLMLM");
		Coordinates coords = rv.shootLasers();
		assertEquals(coords, new CartesianCoordinates(1, 3));
		assertNull(tr.getRoverAt(new CartesianCoordinates(1, 3)));
	}
	
	@Test
	public void testMissedShot(){
		System.out.println("Test shooting and missing...");
		MarsRover rv = new MarsRover(new RoverPosition(new CartesianCoordinates(1, 2), CartesianDirection.N));
		Terrain tr = new Terrain(new CartesianBounds(5, 5));
		tr.addRover(rv);
		Coordinates coords = rv.shootLasers();
		assertNull(coords);
	}
	

}