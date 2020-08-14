package test;

/**
 * 
 * This class runs project
 * 
 * @author Sýddýk Bayram
 */
public class MainRunClass {

	public static void main(String[] args) {

		Thread thread = new Thread(new ZooRunnableClass());
		thread.start();

	}

}
