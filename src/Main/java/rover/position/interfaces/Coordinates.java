package rover.position.interfaces;


public interface Coordinates {
	Coordinates add(Coordinates coords);
	
	boolean isWithinBoundsOf(Bounds bounds);
}
