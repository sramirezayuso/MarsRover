package rover;

import rover.notation.Rotation;
import rover.position.interfaces.Coordinates;

public class MarsRover {
	
	private RoverPosition rvPos;
	private Terrain rvTerrain;
	
	
	
	public void addTerrain(Terrain terrain){
		this.rvTerrain = terrain;
	}
	
	public MarsRover(RoverPosition rvPos){
		this.rvPos = rvPos;
	}
	
	synchronized public void turn(Rotation r){
		switch(r) {
		case L:
			rvPos.setDir(rvPos.getDir().previous());
			break;
		case R:
			rvPos.setDir(rvPos.getDir().next());
			break;
		}
		return;
	}
	
	public boolean move(){
		return rvTerrain.move(this);
	}
	
	public String getHumanReadablePosition(){
		return "" + rvPos.toString();
	}
	
	public RoverPosition getPos(){
		return this.rvPos;
	}
	
	public RoverPosition setPos(RoverPosition pos){
		this.rvPos = pos;
		return this.rvPos;
	}
	public Coordinates shootLasers(){
		return this.rvTerrain.shootLasers(this);
	}
	
	public Terrain getTerrain(){
		return this.rvTerrain;
	}
	
}
