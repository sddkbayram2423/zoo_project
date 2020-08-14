package entities;
/**
 * 
 * Base class of entties.
 * @author Sýddýk Bayram
 */
public abstract class Species {

	private String name;
	
	protected static final int AREA_WIDTH=500;
	
	private int x,y;

	public Species(int x,int y,String name) {
		this.x=x;
		this.y=y;
		this.name =name;
		
	}

	public String getName() {
		return name;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
