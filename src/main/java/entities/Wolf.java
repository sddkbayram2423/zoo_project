package entities;

import tools.Gender;
/**
 * 
 * This class determines the behavior and thevariables of Wolf
 * This class implments Move inteface and inherited from Specie abstract class
 * @author Sýddýk Bayram
 */
public class Wolf extends Species implements Move {

	public static int MOVE_UNIT = 3;

	public static final String NAME = "Wolf";

	private int x, y;

	private Gender gender;

	public Wolf(int x, int y, Gender gender) {
		super(x, y, NAME);
		this.x = x;
		this.y = y;
		this.gender = gender;
	}

	@Override
	public void moveRight(int rightUnit) {


		x += MOVE_UNIT;


		if (x > AREA_WIDTH - MOVE_UNIT) {
			x -= MOVE_UNIT;
		}

	}

	@Override
	public void moveLeft(int leftUnit) {


		x -= MOVE_UNIT;

		if (x - MOVE_UNIT <= 0) {
			x += MOVE_UNIT;
		}
	}

	@Override
	public void moveUp(int upUnit) {

		y += MOVE_UNIT;

		if (y  > AREA_WIDTH - MOVE_UNIT) {
			y -= MOVE_UNIT;
		}

	}

	@Override
	public void moveDown(int downUnit) {
		
		y -= MOVE_UNIT;

		if (y - MOVE_UNIT <= 0) {
			y += MOVE_UNIT;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Wolf [x=" + x + ", y=" + y + ", gender=" + gender + "]";
	}

}
