package rover.telnet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rover.RoverPosition;
import rover.position.cartesian.CartesianCoordinates;
import rover.position.cartesian.CartesianDirection;

public class TelnetClient implements Runnable{

	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
	
	public void run(){
		this.threadPool.execute(new ClientWorkerThread(new RoverPosition(new CartesianCoordinates(0, 0), CartesianDirection.N), "MMRMM"));
		this.threadPool.execute(new ClientWorkerThread(new RoverPosition(new CartesianCoordinates(1, 0), CartesianDirection.N), "MMLMM"));
		this.threadPool.execute(new ClientWorkerThread(new RoverPosition(new CartesianCoordinates(2, 0), CartesianDirection.N), "MMMRM"));
		this.threadPool.execute(new ClientWorkerThread(new RoverPosition(new CartesianCoordinates(3, 0), CartesianDirection.N), "MMMLM"));
		this.threadPool.execute(new ClientWorkerThread(new RoverPosition(new CartesianCoordinates(4, 0), CartesianDirection.N), "MMMMM"));
	}

}
