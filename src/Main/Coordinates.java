package Main;

public class Coordinates {

	private int x;
	private int y;
	
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	Coordinates add(Coordinates coords){
		return new Coordinates(this.x + coords.x, this.y + coords.y);
	}
	
	boolean isWithinBoundsOf(Boundaries bounds){
		if(this.x > bounds.getX() || this.y > bounds.getY()  || this.x < 0 || this.y < 0)
			return false;
		return true;
	}
	
	int getX() {
		return x;
	}
	void setX(int x) {
		this.x = x;
	}
	int getY() {
		return y;
	}
	void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public String toString(){
		return "(" + x + ", " + y +")";
	}
}
