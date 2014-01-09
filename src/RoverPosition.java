
public class RoverPosition {
	private Coordinates coords;
	private MarsRover.Direction dir;
	
	/*public RoverPosition(int x, int y, MarsRover.Direction dir){
		this.coords = new Coordinates(x, y);
		this.dir = dir;
	}*/
	
	public RoverPosition(Coordinates coords, MarsRover.Direction dir){
		this.coords = coords;
		this.dir = dir;
	}
	
	public boolean collides(RoverPosition that){
		if(this.coords.equals(that.coords))
			return true;
		return false;
	}
	
	public RoverPosition projectMove(){
		return new RoverPosition(coords.add(dir.getCoords()), dir);
	}

	public Coordinates getCoords(){
		return coords;
	}

	public MarsRover.Direction getDir() {
		return dir;
	}

	public void setDir(MarsRover.Direction dir) {
		this.dir = dir;
	}
	
	public String toString() {
		return coords.toString() + " " + dir.toString();
	}
}
