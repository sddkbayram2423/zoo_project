package entities;

/**
 * 
 * This class determines the behavior and thevariables of Hen
 * This class implments Move inteface and inherited from Specie abstract class
 * @author S�dd�k Bayram
 */
public class Hen extends  Species implements Move {

	public static int MOVE_UNIT = 1;
	
	public static final String NAME="Hen";

	private int x, y;
	
	public Hen(int x, int y) {
		super(x, y, NAME);
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveRight(int rightUnit) {
		x += MOVE_UNIT;
		
		if (x >AREA_WIDTH-MOVE_UNIT) {
			x-=MOVE_UNIT;
		}

	}

	@Override
	public void moveLeft(int leftUnit) {
		x -= MOVE_UNIT;
		if (x-MOVE_UNIT <= 0) {
			x+=MOVE_UNIT;
		}
	}

	@Override
	public void moveUp(int upUnit) {
		y += MOVE_UNIT;
		if (y>AREA_WIDTH-MOVE_UNIT) {
			y-=MOVE_UNIT;
		}

	}

	@Override
	public void moveDown(int downUnit) {
		y -= MOVE_UNIT;
		if (y-MOVE_UNIT <= 0) {
			y+=MOVE_UNIT;
		}
	}

	// -------------setter/getter-------------------

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

	@Override
	public String toString() { 
		return "Hen [x=" + x + ", y=" + y + " name: "+this.getName()+ "]";
	}
}