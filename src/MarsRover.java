import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
			int height = sc.nextInt();
			int width = sc.nextInt();
			while(sc.hasNext()){
				
				RoverPosition rvPos = new RoverPosition();
				rvPos.loadValues(sc);
				Rover rv = new Rover(rvPos, new Grid(height, width, rvPos));
				
				
				String moves = sc.next();
				rv.receiveMoves(moves);
				
				rv.printPosition();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		remote();
	}
	
	private static void remote(){
		try{
			final int portNumber = 5072;
			System.out.println("Creating server socket on port " + portNumber);
			ServerSocket serverSocket = new ServerSocket(portNumber);
			while (true) {
				Socket socket = serverSocket.accept();
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				Scanner sc = new Scanner(new InputStreamReader(socket.getInputStream()));
				
				pw.println("Grid size:");
				int height = sc.nextInt();
				int width = sc.nextInt();
				
				pw.println("Rover position:");
				RoverPosition rvPos = new RoverPosition();
				rvPos.loadValues(sc);
				Rover rv = new Rover(rvPos, new Grid(height, width, rvPos));
				rv.setOutput(os);
				
				pw.println("Instructions:");
				String moves = sc.next();
				rv.receiveMoves(moves);

				
				pw.println("" + rvPos.getX() + " " + rvPos.getY() + " " + rvPos.getDir().toString());
	
				pw.close();
				socket.close();
	
				//System.out.println("Just said hello to:" + str);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static class Rover {
		private RoverPosition rvPos;
		private Grid rvGrid;
		private PrintWriter pw;
		
		Rover(RoverPosition rvPos, Grid rvGrid){
			this.rvPos = rvPos;
			this.rvGrid = rvGrid;
			this.pw = new PrintWriter(System.out, true);
		}
		
		void turn(Rotation r){
			switch(r) {
			case L:
				rvPos.setDir(rvPos.getDir().previous());
				break;
			case R:
				rvPos.setDir(rvPos.getDir().next());
				break;
			}
			return;
		}
		
		void move(){
			switch(rvPos.getDir()) {
			case N:
				rvPos.setY(rvPos.getY()+1);
				break;
			case E:
				rvPos.setX(rvPos.getX()+1);
				break;
			case S:
				rvPos.setY(rvPos.getY()-1);
				break;
			case W:
				rvPos.setX(rvPos.getX()-1);
				break;
			}
		}
		
		void receiveMoves(String moves){
			for(int i=0; i<moves.length(); i++){
				pw.println(rvGrid);
				if(moves.charAt(i) == 'L')
					this.turn(Rotation.L);
				else if(moves.charAt(i) == 'R')
					this.turn(Rotation.R);
				else
					this.move();
			}
			pw.println(rvGrid);
		}
		
		void printPosition(){
			System.out.println("" + rvPos.getX() + rvPos.getY() + rvPos.getDir());
		}
		
		void setOutput(OutputStream os){
			this.pw	= new PrintWriter(os, true);
		}
	}
}
