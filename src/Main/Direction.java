package Main;

public interface Direction {
	
	Direction next();
	
	Direction previous();
	
	Coordinates getCoords();

}
