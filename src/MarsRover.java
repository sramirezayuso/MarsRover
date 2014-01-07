import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarsRover {
	public enum Direction {
	    N, E, S, W;
	    
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
	    
	    public String toString(){
	    	switch(this.ordinal()){
	    	case 0:
	    		return "N";
	    	case 1: 
	    		return "E";
	    	case 2:
	    		return "S";
	    	case 3:
	    		return "W";
	    	}
	    	return "";
	    }
	}
	
	public enum Rotation {
		L, R
	}
	
	public static void main(String[] args){
		try {
			Scanner sc = new Scanner(new File("input.in"));
			sc.nextLine();
			while(sc.hasNext()){
				Rover rv = new Rover(0,0, Direction.N);
				int x = sc.nextInt();
				int y = sc.nextInt();
				String dir = sc.next();
				if(dir.equals("N"))
					rv = new Rover(x, y, Direction.N);
				else if(dir.equals("E"))
					rv = new Rover(x, y, Direction.E);
				else if(dir.equals("S"))
					rv = new Rover(x, y, Direction.S);
				else if(dir.equals("W"))
					rv = new Rover(x, y, Direction.W);
				
				String moves = sc.next();
				for(int i=0; i<moves.length(); i++){
					if(moves.charAt(i) == 'L')
						rv.turn(Rotation.L);
					else if(moves.charAt(i) == 'R')
						rv.turn(Rotation.R);
					else
						rv.move();
				}
				
				rv.printPosition();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static class Rover {
		private int x;
		private int y;
		private Direction dir;
		
		Rover(int x, int y, Direction dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		void turn(Rotation r){
			switch(r) {
			case L:
				dir = dir.previous();
				break;
			case R:
				dir = dir.next();
				break;
			}
			return;
		}
		
		void move(){
			switch(dir) {
			case N:
				y += 1;
				break;
			case E:
				x += 1;
				break;
			case S:
				y -= 1;
				break;
			case W:
				x -= 1;
				break;
			}
		}
		
		void printPosition(){
			System.out.println("" + x + y + dir);
		}
	}
}
