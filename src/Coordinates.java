
public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinates add(Coordinates coords){
		return new Coordinates(this.x + coords.x, this.y + coords.y);
	}
	
	public boolean isWithinBoundsOf(Coordinates bounds){
		if(this.x > bounds.x || this.y > bounds.y || this.x < 0 || this.y < 0)
			return false;
		return true;
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
	
	public String toString(){
		return "(" + x + ", " + y +")";
	}
}
