package rover.notation;

import rover.MarsRover;

public class LRMSNotation implements CommandNotation {
	
	@Override
	public void processMoves(MarsRover rv, String moves){
		
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L'){
				rv.turn(Rotation.L);
				System.out.println("Turning rover at " + rv.getPos().getCoords().toString() + " to the left.");
			}
			else if(moves.charAt(i) == 'R'){
				rv.turn(Rotation.R);
				System.out.println("Turning rover at " + rv.getPos().getCoords().toString() + " to the right.");
			}
			else if(moves.charAt(i) == 'M'){
				synchronized(rv.getTerrain()) {
					System.out.print("Moving rover at " + rv.getPos().getCoords().toString());
					if(!rv.move()){
						System.out.println("\nRover ran into a wall or another rover.");
						return;
					}
					System.out.println(" to " + rv.getPos().getCoords().toString() +".");	
				}
			} else if(moves.charAt(i) == 'S')
				rv.shootLasers();
		}
	}
}
