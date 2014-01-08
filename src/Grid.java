public class Grid {
	private String[][] grid;
	private RoverPosition rvPos;
	
	public Grid(int height, int width, RoverPosition rvPos){
		this.grid = new String[width][height];
		this.rvPos = rvPos;
	}
	
	public String toString(){
		String str = "";
		String line = "";
		for (int i = 0; i < grid.length; i++) {
			line += "-";
		}
		line += "\n";
		str += line;
		for (int i = grid[0].length-1; i >= 0 ; i--) {
			for (int j = 0; j < grid.length; j++) {
				str += "|";
				if(j == rvPos.getX() && i == rvPos.getY())
					str += rvPos.getDir();
				else
					str += " ";
			}
			str += "|\n";
			str += line;
		}
		return str;
	}
	
}
