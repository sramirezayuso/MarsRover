import java.util.Scanner;

public class RoverPosition {
	private int x;
	private int y;
	private MarsRover.Direction dir;
	
	public void loadValues(Scanner sc){
		this.x = sc.nextInt();
		this.y = sc.nextInt();
		String strDir = sc.next();
		if(strDir.equals("N"))
			this.dir = MarsRover.Direction.N;
		else if(strDir.equals("E"))
			this.dir = MarsRover.Direction.E;
		else if(strDir.equals("S"))
			this.dir = MarsRover.Direction.S;
		else if(strDir.equals("W"))
			this.dir = MarsRover.Direction.W;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public MarsRover.Direction getDir() {
		return dir;
	}

	public void setDir(MarsRover.Direction dir) {
		this.dir = dir;
	}
}
