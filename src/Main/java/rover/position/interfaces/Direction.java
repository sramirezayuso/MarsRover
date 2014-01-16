package rover.position.interfaces;



public interface Direction {
	
	Direction next();
	
	Direction previous();
	
	Coordinates getCoords();

}
