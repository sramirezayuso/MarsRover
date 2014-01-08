import java.util.LinkedList;
import java.util.List;

public class Terrain {
	//private String[][] grid;
	private int height;
	private int width;
	private List<MarsRover> rovers;
	
	public Terrain(int height, int width){
		this.height = height;
		this.width = width;
		//this.grid = new String[width][height];
		this.rovers = new LinkedList<MarsRover>();
	}
	
	public boolean addRover(MarsRover rv){
		boolean outOfBounds = checkOutOfBounds(rv.getPos());
		boolean collision = checkCollision(rv.getPos());
		if(!outOfBounds && !collision){
			rovers.add(rv);
			return true;
		}
		return false;
	}
	
	private boolean checkOutOfBounds(RoverPosition pos){
		if(pos.getX() >= width || pos.getX() < 0 || pos.getY() >= height || pos.getY() < 0)
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
	
	public boolean move(MarsRover rv){
		RoverPosition oldPos = rv.getPos();
		RoverPosition newPos = oldPos.simulateMove();
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
