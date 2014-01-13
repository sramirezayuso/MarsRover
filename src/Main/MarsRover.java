package Main;

public class MarsRover {
	
	private RoverPosition rvPos;
	private Terrain rvTerrain;
	
	
	
	public void addTerrain(Terrain terrain){
		this.rvTerrain = terrain;
	}
	
	enum Rotation {
		L, R
	}
	
	public MarsRover(RoverPosition rvPos){
		this.rvPos = rvPos;
	}
	
	public void turn(Rotation r){
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
	
}
