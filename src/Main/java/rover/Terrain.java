package rover;
import java.util.HashMap;
import java.util.Map;

import rover.position.interfaces.Bounds;
import rover.position.interfaces.Coordinates;

public class Terrain {
	private Bounds bounds;
	private Map<Coordinates, MarsRover> rovers;
	
	public Terrain(Bounds bounds){
		this.bounds = bounds;
		this.rovers = new HashMap<Coordinates, MarsRover>();
	}
	
	synchronized public boolean addRover(MarsRover rv){
		boolean outOfBounds = checkOutOfBounds(rv.getPos());
		boolean collision = checkCollision(rv.getPos());
		if(!outOfBounds && !collision){
			rovers.put(rv.getPos().getCoords(), rv);
			rv.addTerrain(this);
			return true;
		}
		return false;
	}
	
	public MarsRover getRoverAt(Coordinates coords){
		return rovers.get(coords);
	}
	
	Coordinates shootLasers(MarsRover rv){
		boolean outOfBounds = false;
		boolean collision = false;
		RoverPosition newPos = rv.getPos();
		do {
			RoverPosition oldPos = newPos;
			newPos = oldPos.projectMove();
			outOfBounds = checkOutOfBounds(newPos);
			collision = checkCollision(newPos);
		} while(!outOfBounds && !collision);
		if(collision){
			rovers.remove(newPos.getCoords());
			return newPos.getCoords();
		}
		return null;
	}
	
	boolean move(MarsRover rv){
		RoverPosition oldPos = rv.getPos();
		RoverPosition newPos = oldPos.projectMove();
		boolean outOfBounds = checkOutOfBounds(newPos);
		boolean collision = checkCollision(newPos);
		if(collision && rovers.get(newPos.getCoords()).equals(rv))
			collision = false;
		if(!outOfBounds && !collision){
			rovers.remove(rv.getPos().getCoords());
			rv.setPos(newPos);
			rovers.put(rv.getPos().getCoords(), rv);
			return true;
		}
		return false;
	}
	
	private boolean checkOutOfBounds(RoverPosition pos){
		if(!pos.getCoords().isWithinBoundsOf(bounds))
			return true;
		return false;
	}
	
	private boolean checkCollision(RoverPosition pos){
		if(rovers.containsKey(pos.getCoords()))
			return true;
		return false;
	}
	
}
