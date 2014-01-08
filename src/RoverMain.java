import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class RoverMain {
	public static void main(String[] args){
		telnet();
	}
	
	private static boolean processFile(){
		try {
			Scanner sc = new Scanner(new File("input.in"));
			int height = sc.nextInt();
			int width = sc.nextInt();
			while(sc.hasNext()){
				
				RoverPosition rvPos = loadValues(sc);
				
				MarsRover rv = new MarsRover(rvPos);
				Terrain tr = new Terrain(height, width);
				if(!tr.addRover(rv))
					return false;
				rv.addTerrain(tr);
							
				String moves = sc.next();
				rv.receiveMoves(moves);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private static boolean telnet(){
		try{
			final int portNumber = 5072;
			System.out.println("Creating server socket on port " + portNumber);
			ServerSocket serverSocket = new ServerSocket(portNumber);
			PrintWriter pw;
			while (true) {
				Socket socket = serverSocket.accept();
				OutputStream os = socket.getOutputStream();
				pw = new PrintWriter(os, true);
				Scanner sc = new Scanner(new InputStreamReader(socket.getInputStream()));
				
				pw.println("Grid size:");
				int height = sc.nextInt();
				int width = sc.nextInt();
				
				pw.println("Rover position:");
				RoverPosition rvPos = loadValues(sc);
				MarsRover rv = new MarsRover(rvPos);
				Terrain tr = new Terrain(height, width);
				if(!tr.addRover(rv))
					return false;
				rv.addTerrain(tr);
				
				pw.println("Instructions:");
				String moves = sc.next();
				rv.receiveMoves(moves);
			
				pw.println("" + rv.getPos().getX() + " " + rv.getPos().getY() + " " + rv.getPos().getDir());
				pw.close();
				socket.close();

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;
	}

	private static RoverPosition loadValues(Scanner sc){
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		String strDir = sc.next();
		MarsRover.Direction dir = MarsRover.Direction.N;
		if(strDir.equals("N"))
			dir = MarsRover.Direction.N;
		else if(strDir.equals("E"))
			dir = MarsRover.Direction.E;
		else if(strDir.equals("S"))
			dir = MarsRover.Direction.S;
		else if(strDir.equals("W"))
			dir = MarsRover.Direction.W;
		
		return new RoverPosition(x, y, dir);
	}

}

