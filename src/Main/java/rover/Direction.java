package rover;

public interface Direction {
	
	Direction next();
	
	Direction previous();
	
	Coordinates getCoords();

}
