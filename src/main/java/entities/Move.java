package entities;

/**
 * 
 * This inteface determines the behavior of entities
 * 
 * @author S�dd�k Bayram
 */
public interface Move {

	public void moveRight(int rightUnit);

	public void moveLeft(int leftUnit);

	public void moveUp(int upUnit);

	public void moveDown(int downUnit);

}
