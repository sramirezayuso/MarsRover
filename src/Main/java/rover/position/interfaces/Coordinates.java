package rover.position.interfaces;


public interface Coordinates {
	Coordinates add(Coordinates coords);
	
	boolean isWithinBoundsOf(Bounds bounds);
	
	@Override
	public int hashCode();
	
	@Override
	public boolean equals(Object obj);
	
	@Override
	public String toString();
	
	public String machineString();
}
