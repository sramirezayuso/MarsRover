
public class RoverPosition {
	private int x;
	private int y;
	private MarsRover.Direction dir;
	
	public RoverPosition(int x, int y, MarsRover.Direction dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public boolean collides(RoverPosition that){
		if(this.x == that.x && this.y == that.y)
			return true;
		return false;
	}
	
	public RoverPosition simulateMove(){
		return new RoverPosition(x + dir.getX(), y + dir.getY(), dir);
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
