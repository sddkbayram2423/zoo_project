package test;

/**
 * 
 * This class runs project
 * 
 * @author S�dd�k Bayram
 */
public class MainRunClass {

	public static void main(String[] args) {

		Thread thread = new Thread(new ZooRunnableClass());
		thread.start();

	}

}
