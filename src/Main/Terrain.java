package Main;
import java.util.LinkedList;
import java.util.List;

public class Terrain {
	private Bounds bounds;
	private List<MarsRover> rovers;
	
	public Terrain(Bounds bounds){
		this.bounds = bounds;
		this.rovers = new LinkedList<MarsRover>();
	}
	
	public boolean addRover(MarsRover rv){
		boolean outOfBounds = checkOutOfBounds(rv.getPos());
		boolean collision = checkCollision(rv.getPos());
		if(!outOfBounds && !collision){
			rovers.add(rv);
			rv.addTerrain(this);
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
		for(MarsRover oldRv : rovers){
			if(oldRv.getPos().collides(pos))
				return true;
		}
		return false;
	}
	
	boolean move(MarsRover rv){
		RoverPosition oldPos = rv.getPos();
		RoverPosition newPos = oldPos.projectMove();
		boolean outOfBounds = checkOutOfBounds(newPos);
		boolean collision = checkCollision(newPos);
		if(!outOfBounds && !collision){
			rv.setPos(newPos);
			return true;
		}
		return false;
	}
	
	/*public String toString(){
		String str = "";
		String line = "";
		for (int i = 0; i < grid.length*2+1; i++) {
			line += "-";
		}
		line += "\n";
		str += line;
		for (int i = grid[0].length-1; i >= 0 ; i--) {
			for (int j = 0; j < grid.length; j++) {
				str += "|";
				if(j == rvPos.getX() && i == rvPos.getY())
					str += rvPos.getDir();
				else
					str += " ";
			}
			str += "|\n";
			str += line;
		}
		return str;
	}*/
	
}
