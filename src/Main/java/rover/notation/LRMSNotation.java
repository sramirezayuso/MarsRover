package rover.notation;

import rover.MarsRover;

public class LRMSNotation implements CommandNotation {
	
	@Override
	public void processMoves(MarsRover rv, String moves){
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L')
				rv.turn(Rotation.L);
			else if(moves.charAt(i) == 'R')
				rv.turn(Rotation.R);
			else if(moves.charAt(i) == 'M')
				if(!rv.move())
					return;
			else if(moves.charAt(i) == 'S')
				rv.shootLasers();
		}
	}
	
}
