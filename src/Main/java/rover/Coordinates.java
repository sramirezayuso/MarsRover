package rover;

public interface Coordinates {
	Coordinates add(Coordinates coords);
	
	boolean isWithinBoundsOf(Bounds bounds);
}
