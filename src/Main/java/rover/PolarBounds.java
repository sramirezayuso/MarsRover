package rover;

public class PolarBounds implements Bounds{
	private int r;
	
	public PolarBounds(int r){
		if(r < 0)
			throw new IllegalArgumentException();
		this.r = r;
	}
	
	int getR() {
		return r;
	}
}
