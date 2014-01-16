package rover;


public class RoverTelnetMain {
	public static void main(String[] args){
		TelnetServer server = new TelnetServer();
		server.run();
	}
}

