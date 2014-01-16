package rover.position.polar;

import rover.position.interfaces.Direction;

public enum PolarDirection implements Direction{
	 N(1, Math.PI/2), NE(1, Math.PI/4), E(1,0), SE(1, -Math.PI/4), S(1,-Math.PI/2), SW(1, -3*Math.PI/4), W(1,Math.PI), NW(1, 3*Math.PI/4);
	    
	private PolarCoordinates coords;
	
	private PolarDirection(double r, double thetha){
		this.coords = new PolarCoordinates(r, thetha);
	}
	
	@Override
	public Direction next(){  
        int order = this.ordinal(); 
        int newSpot = 0;  
        PolarDirection result = this;
        if(order == 7){  
            newSpot = 0;  
        } else {  
            newSpot = order + 1;  
        }  
        for(PolarDirection val : PolarDirection.values()){  
            if(val.ordinal() == newSpot){  
                result = val;  
            }  
        }  
        return result;  
    } 
    
	@Override
    public Direction previous(){  
        int order = this.ordinal(); 
        int newSpot = 0;  
        PolarDirection result = this;  
        if(order == 0){  
            newSpot = 7;  
        } else {  
            newSpot = order - 1;  
        }  
        for(PolarDirection val : PolarDirection.values()){  
            if(val.ordinal() == newSpot){  
                result = val;  
            }  
        }  
        return result;  
    }
    
	@Override
    public PolarCoordinates getCoords(){
    	return coords;
    }
}
