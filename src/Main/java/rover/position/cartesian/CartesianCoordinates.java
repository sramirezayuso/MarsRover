package rover.position.cartesian;

import rover.position.interfaces.Bounds;
import rover.position.interfaces.Coordinates;

public class CartesianCoordinates implements Coordinates{

	private int x;
	private int y;
	
	public CartesianCoordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinates add(Coordinates coords){
		if(!(coords instanceof CartesianCoordinates))
			throw new IllegalArgumentException();
		CartesianCoordinates cCoords = (CartesianCoordinates) coords;
		return new CartesianCoordinates(this.x + cCoords.x, this.y + cCoords.y);
	}
	
	public boolean isWithinBoundsOf(Bounds bounds){
		if(!(bounds instanceof CartesianBounds))
			throw new IllegalArgumentException();
		CartesianBounds cBounds = (CartesianBounds) bounds;
		if(this.x > cBounds.getX() || this.y > cBounds.getY()  || this.x < 0 || this.y < 0)
			return false;
		return true;
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
		CartesianCoordinates other = (CartesianCoordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "(" + x + ", " + y +")";
	}
	
	public String machineString(){
		return "" + x + " " + y;
	}
}
