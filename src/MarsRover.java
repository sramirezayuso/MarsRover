
public class MarsRover {
	
	private RoverPosition rvPos;
	private Terrain rvTerrain;
	
	public enum Direction {
	    N(0, 1), E(1,0), S(0,-1), W(-1,0);
	    
	    private Coordinates coords;
	    
	    private Direction(int x, int y){
	    	this.coords = new Coordinates(x, y);
	    }
	    
	    public Direction next(){  
            int order = this.ordinal(); 
            int newSpot = 0;  
            Direction result = this;  
            if(order == 3){  
                newSpot = 0;  
            } else {  
                newSpot = order + 1;  
            }  
            for(Direction val : Direction.values()){  
                if(val.ordinal() == newSpot){  
                    result = val;  
                }  
            }  
            return result;  
        } 
	    
	    public Direction previous(){  
            int order = this.ordinal(); 
            int newSpot = 0;  
            Direction result = this;  
            if(order == 0){  
                newSpot = 3;  
            } else {  
                newSpot = order - 1;  
            }  
            for(Direction val : Direction.values()){  
                if(val.ordinal() == newSpot){  
                    result = val;  
                }  
            }  
            return result;  
        }
	    
	    public Coordinates getCoords(){
	    	return coords;
	    }
	    
	}
	
	public void addTerrain(Terrain terrain){
		this.rvTerrain = terrain;
	}
	
	public enum Rotation {
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
	
	public void receiveMoves(String moves){
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L')
				this.turn(Rotation.L);
			else if(moves.charAt(i) == 'R')
				this.turn(Rotation.R);
			else
				rvTerrain.move(this);
		}
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
