package rover.position.cartesian;

import rover.position.interfaces.Direction;



public enum CartesianDirection implements Direction {
    N(0, 1), E(1,0), S(0,-1), W(-1,0);
    
    private CartesianCoordinates coords;
    
    private CartesianDirection(int x, int y){
    	this.coords = new CartesianCoordinates(x, y);
    }
    
    @Override
    public Direction next(){  
        int order = this.ordinal(); 
        int newSpot = 0;  
        CartesianDirection result = this;  
        if(order == 3){  
            newSpot = 0;  
        } else {  
            newSpot = order + 1;  
        }  
        for(CartesianDirection val : CartesianDirection.values()){  
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
        CartesianDirection result = this;  
        if(order == 0){  
            newSpot = 3;  
        } else {  
            newSpot = order - 1;  
        }  
        for(CartesianDirection val : CartesianDirection.values()){  
            if(val.ordinal() == newSpot){  
                result = val;  
            }  
        }  
        return result;  
    }
    
    @Override
    public CartesianCoordinates getCoords(){
    	return coords;
    }
    
}