package test;



import manager.MoveManager;
/**
 * 
 * This class called when main a MainRunClass creat a Threat and start it
 * 
 * @author Sýddýk Bayram
 */

public class ZooRunnableClass implements Runnable {

	private MoveManager moveManager;
	
	private boolean moveAvability;

	public ZooRunnableClass() {

		moveManager = new MoveManager();
		
		moveAvability = moveManager.isMoveAbilityIsAvaible();
		
	}

	@Override
	public void run() {

		moveManager.moveObjects();

		
		//This loop works until total move unit reach 1000 unit
		while (moveAvability) {
		
			moveManager.moveObjects();
			moveManager.showMoveMessage();
			moveAvability = moveManager.isMoveAbilityIsAvaible();
			
			try {
				//In every move thread paused for 0.1 second
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		moveManager.shownEntitiesTotalCount();
		

	}

}
