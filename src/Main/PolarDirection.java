package Main;

public enum PolarDirection implements Direction{
	 N(1, Math.PI/2), E(1,0), S(1,-Math.PI/2), W(1,Math.PI);
	    
	private PolarCoordinates coords;
	
	private PolarDirection(double r, double thetha){
		this.coords = new PolarCoordinates(r, thetha);
	}
	
	@Override
	public Direction next(){  
        int order = this.ordinal(); 
        int newSpot = 0;  
        PolarDirection result = this;
        if(order == 3){  
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
            newSpot = 3;  
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
