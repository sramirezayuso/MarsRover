package rover;
import rover.MarsRover.Rotation;


public class RoverTelnetMain {
	public static void main(String[] args){
		TelnetServer server = new TelnetServer();
		server.run();
	}
	
	
	public static void processMoves(MarsRover rv, String moves){
		for(int i=0; i<moves.length(); i++){
			if(moves.charAt(i) == 'L')
				rv.turn(Rotation.L);
			else if(moves.charAt(i) == 'R')
				rv.turn(Rotation.R);
			else if(moves.charAt(i) == 'M')
				rv.move();
			else if(moves.charAt(i) == 'S')
				rv.shootLasers();
		}
	}
}

