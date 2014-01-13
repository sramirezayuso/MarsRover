package Main;

public class RoverPosition {
	private Coordinates coords;
	private Direction dir;
	
	/*public RoverPosition(int x, int y, MarsRover.Direction dir){
		this.coords = new Coordinates(x, y);
		this.dir = dir;
	}*/
	
	public RoverPosition(Coordinates coords, Direction dir){
		this.coords = coords;
		this.dir = dir;
	}
	
	boolean collides(RoverPosition that){
		if(this.coords.equals(that.coords))
			return true;
		return false;
	}
	
	RoverPosition projectMove(){
		return new RoverPosition(coords.add(dir.getCoords()), dir);
	}

	public Coordinates getCoords(){
		return coords;
	}

	public Direction getDir() {
		return dir;
	}

	void setDir(Direction dir) {
		this.dir = dir;
	}
	
	public String toString() {
		return coords.toString() + " " + dir.toString();
	}
}
