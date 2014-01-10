package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Main.MarsRover.Rotation;


public class RoverMain {
	public static void main(String[] args){
		TelnetServer server = new TelnetServer();
		server.run();
	}
	
	private static boolean processFile(){
		Scanner sc = null;
		try {
			sc = new Scanner(new File("input.in"));
			int height = sc.nextInt();
			int width = sc.nextInt();
			while(sc.hasNext()){
				
				RoverPosition rvPos = loadValues(sc);
				
				MarsRover rv = new MarsRover(rvPos);
				Terrain tr = new Terrain(new Boundaries(width-1, height-1));
				if(!tr.addRover(rv))
					return false;
							
				String moves = sc.next();
				processMoves(rv, moves);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(sc != null)
				sc.close();
		}
		return true;
	}
	
	public static void processMoves(MarsRover rv, String moves){
		for(int i=0; i<moves.length(); i++){
			//System.out.println(rv.getHumanReadablePosition());
			if(moves.charAt(i) == 'L')
				rv.turn(Rotation.L);
			else if(moves.charAt(i) == 'R')
				rv.turn(Rotation.R);
			else
				rv.move();
		}
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
		
		return new RoverPosition(new Coordinates(x, y), dir);
	}
	
	/*private static boolean telnet(){
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
				Terrain tr = new Terrain(new Coordinates(width-1, height-1));
				if(!tr.addRover(rv))
					return false;
				
				pw.println("Instructions:");
				String moves = sc.next();
				processMoves(rv, moves);
				
				pw.println(rv.getPos());
				pw.close();
				socket.close();

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;
	}*/



}

