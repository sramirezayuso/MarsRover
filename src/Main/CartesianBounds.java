package Main;

public class CartesianBounds implements Bounds {
	private int x;
	private int y;
	
	public CartesianBounds(int x, int y){
		if(x < 0 || y < 0)
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}
	
	
}
