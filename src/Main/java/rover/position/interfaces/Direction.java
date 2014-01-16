package rover.position.interfaces;



public interface Direction {
	
	Direction next();
	
	Direction previous();
	
	Coordinates getCoords();
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public int hashCode();

}
