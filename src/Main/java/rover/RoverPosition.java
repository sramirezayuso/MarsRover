package rover;

import rover.position.interfaces.Coordinates;
import rover.position.interfaces.Direction;

public class RoverPosition {
	private Coordinates coords;
	private Direction dir;

	public RoverPosition(Coordinates coords, Direction dir){
		this.coords = coords;
		this.dir = dir;
	}
	
	RoverPosition projectMove(){
		return new RoverPosition(coords.add(dir.getCoords()), dir);
	}

	public Coordinates getCoords(){
		return coords;
	}

	public Direction getDir() {
		return dir;
	}
	public String toString() {
		return coords.toString() + " " + dir.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coords == null) ? 0 : coords.hashCode());
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
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
		RoverPosition other = (RoverPosition) obj;
		if (coords == null) {
			if (other.coords != null)
				return false;
		} else if (!coords.equals(other.coords))
			return false;
		if (dir == null) {
			if (other.dir != null)
				return false;
		} else if (!dir.equals(other.dir))
			return false;
		return true;
	}
}
