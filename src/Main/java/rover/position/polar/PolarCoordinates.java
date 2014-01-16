package rover.position.polar;

import rover.position.interfaces.Bounds;
import rover.position.interfaces.Coordinates;

public class PolarCoordinates implements Coordinates{
	
	private double r;
	private double thetha;
	
	public PolarCoordinates(double r, double thetha){
		if(r < 0)
			throw new IllegalArgumentException();
		this.r = r;
		this.thetha = thetha;
	}

	@Override
	public Coordinates add(Coordinates coords) {
		if(!(coords instanceof PolarCoordinates))
			throw new IllegalArgumentException();
		PolarCoordinates pCoords = (PolarCoordinates) coords;
		double x = r*Math.cos(thetha);
		double y = r*Math.sin(thetha);
		double pcx = pCoords.r *Math.cos(pCoords.thetha);
		double pcy = pCoords.r *Math.sin(pCoords.thetha);
		return new PolarCoordinates(Math.sqrt(Math.pow(x+pcx, 2) + Math.pow(y+pcy, 2)), Math.atan2(y+pcy, x+pcx));
	}

	@Override
	public boolean isWithinBoundsOf(Bounds bounds) {
		if(!(bounds instanceof PolarBounds))
			throw new IllegalArgumentException();
		PolarBounds pBounds = (PolarBounds) bounds;
		if(this.r < pBounds.getR())
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(thetha);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		PolarCoordinates other = (PolarCoordinates) obj;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		if (Double.doubleToLongBits(thetha) != Double
				.doubleToLongBits(other.thetha))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "(" + r + ", " + thetha +")";
	}
	
	public double getR(){
		return r;
	}
	
	public double getThetha(){
		return thetha;
	}

}
