package manager;

import java.util.ArrayList;



import java.util.Random;

import entities.Cock;
import entities.Cow;
import entities.Hen;
import entities.Hunter;
import entities.Lion;
import entities.Sheep;
import entities.Wolf;
import tools.EntitiyLocation;
/**
 * 
 * This class was created to manage random move of entities
 * 
 * @author Sýddýk Bayram
 */
public class MoveManager {
	
	private String[] objectNames = { "Hunter", "Lion", "Cow", "Cock", "Wolf", "Sheep", "Hen" };

	private String[] objectDirection = { "Right", "Left", "Up", "Down" };
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	//Random counts to chooce entity to next move
	private Random chooseRandomObject;
	
	private Random chooseRandomDirection;
	
	private Random chooseRandomHen;
	
	private Random chooseRandomCow;
	
	private Random chooseRandomLion;
	
	private Random chooseRandomCock;
	
	private Random chooseRandomWolf;
	
	private Random chooseRandomSheep;
	
	//----------------------------------------------------------------------------------------------------------------------------------------

	
	//Entites
	private ArrayList<Hen> hens;
	
	private ArrayList<Cow> cows;
	
	private ArrayList<Cock> cocks;
	
	private ArrayList<Lion> lions;
	
	private ArrayList<Wolf> wolfs;
	
	private ArrayList<Sheep> sheeps;

	private Hunter hunter;

	private EntityManager entityManager;
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	private int totalMoveUnit = 0;

	private boolean moveAbilityIsAvaible = true;

	private String moveMessage="";
	
	private ConfrontrationCheckManager confrontration;
	
	//----------------------------------------------------------------------------------------------------------------------------------------

	//Constructor
	public MoveManager() {

        entityManager = EntityManager.getIntanceEntityManager();
        
        
		//Intialize random counts
		chooseRandomObject = new Random();
		
		chooseRandomDirection = new Random();
		
		chooseRandomHen = new Random();
		
		chooseRandomCow = new Random();
		
		chooseRandomCock=new Random();
		
		chooseRandomLion = new Random();
		
		chooseRandomWolf = new Random();
		
		chooseRandomSheep = new Random();
		
		
		//Get entites from EntityManager class
		hunter = entityManager.getHunter();

		hens = entityManager.getHens();

		cows = entityManager.getCows();

		lions = entityManager.getLions();

		cocks = entityManager.getCocks();
		
		wolfs = entityManager.getWolfs();

		sheeps = entityManager.getSheeps();
		
		confrontration=new ConfrontrationCheckManager();

	}
	//----------------------------------------------------------------------------------------------------------------------------------------

	
	//This function choose random entity to move in next move
	public void moveObjects() {
		
		
		//switch loop never used if total move unit more than 1000 unit 
		if(!moveAbilityIsAvaible) {
			return;
		}
		
		String randomObjectName=objectNames[chooseRandomObject.nextInt(objectNames.length)];
		
		switch (randomObjectName) {
		case "Hunter":
			moveHunter(chooseRandomDirection.nextInt(objectDirection.length));
			break;
		case "Lion":
			moveLion(chooseRandomDirection.nextInt(objectDirection.length));
			break;
		case "Cow":
			moveCow(chooseRandomDirection.nextInt(objectDirection.length));
			break;
		case "Cock":
			moveCock(chooseRandomDirection.nextInt(objectDirection.length));
			break;
		case "Wolf":
			moveWolf(chooseRandomDirection.nextInt(objectDirection.length));
			break;
		case "Sheep":
			moveSheep(chooseRandomDirection.nextInt(objectDirection.length));
			break;
		case "Hen":
			moveHen(chooseRandomDirection.nextInt(objectDirection.length));
			break;
		}
		
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	/*
	This function is used when switch loop, which in moveObjects function, choose case: "Sheep"
	Than the switch loop, which in this function, choose random direction for random selected sheep and it will move to the direction
	Before end of this function, checked selected sheep's position relative to enemies(Wolf,Lion,Hunter) or other sheeps.
	If the sheep moves in danger distance of enemies it is going to remove from sheeps list
    If the sheep moves less than 3 units from any sheep Entitymanger class is going to create random sheep and it will added sheeps list
	 */
	private void moveSheep(int direction) {
		
		String directionName=objectDirection[direction];
		
		int sheepIndex=chooseRandomSheep.nextInt(sheeps.size());

		Sheep sheep = sheeps.get(sheepIndex);
		
		EntitiyLocation oldLocation=new EntitiyLocation(sheep.getX(), sheep.getY());
		
		switch (directionName) {
		case "Right":

			if (sheep != null) {
				
				sheep.moveRight(Sheep.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(sheep.getX(), sheep.getY()));
				moveMessage=sheepIndex+ ". sheep move to right direction "+ Sheep.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Left":

			if (sheep != null) {
				sheep.moveLeft(Sheep.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(sheep.getX(), sheep.getY()));
				moveMessage=sheepIndex+ ". sheep move to left direction "+ Sheep.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			
			break;
		case "Up":

			if (sheep != null) {
				sheep.moveUp(Sheep.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(sheep.getX(), sheep.getY()));
				moveMessage=sheepIndex+ ". sheep move to up direction "+ Sheep.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Down":

			if (sheep != null) {
				sheep.moveDown(Sheep.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(sheep.getX(), sheep.getY()));
				moveMessage=sheepIndex+ ". sheep move to down direction "+ Sheep.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		}
		confrontration.checkSheepWithOtherSheeps(sheep);
		confrontration.checkSheepWithEnemies(sheep);
		isMoveAbilityAvaible(Sheep.MOVE_UNIT);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------

	
	/*
	This function is used when switch loop, which in moveObjects function, choose case: "Wolf"
	Than the switch loop, which in this function, choose random direction for random selected wolf and it will move to the direction
	Before end of this function, checked selected wolf's position relative to enemiy(Hunter) or other wolfs.
	If the wolf moves  within 4 units of any of its prey, that animal will removed its list
	If the wolf moves in danger distance of enemiy it is going to remove from wolfs list
   	If the wolf moves less than 3 units from any wolf Entitymanger class is going to create random wolf and it will added sheeps list
	 */
	private void moveWolf(int direction) {
		
		String directionName=objectDirection[direction];

		int wolfIndex=chooseRandomWolf.nextInt(wolfs.size());
		Wolf wolf = wolfs.get(wolfIndex);
		
		EntitiyLocation oldLocation=new EntitiyLocation(wolf.getX(), wolf.getY());
		

		switch (directionName) {
		case "Right":

			if (wolf != null) {
				
				wolf.moveRight(Wolf.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(wolf.getX(), wolf.getY()));
				moveMessage=wolfIndex+". wolf move to right direction "+ Wolf.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Left":

			if (wolf != null) {
				wolf.moveLeft(Wolf.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(wolf.getX(), wolf.getY()));
				moveMessage=wolfIndex+". wolf  move to left direction "+ Wolf.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Up":

			if (wolf != null) {
				wolf.moveUp(Wolf.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(wolf.getX(), wolf.getY()));
				moveMessage=wolfIndex+". wolf  move to up direction "+ Wolf.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;;
			}

			break;
		case "Down":

			if (wolf != null) {
				wolf.moveDown(Wolf.MOVE_UNIT);
				moveMessage=wolfIndex+". wolf  move to down direction "+ Wolf.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;;
			}

			break;
		}
		
		confrontration.checkWolfWithOherCocks(wolf, wolfIndex);
		confrontration.checkWolfWithOtherHens(wolf, wolfIndex);
		confrontration.checkWolfWithOtherSheeps(wolf, wolfIndex);
		confrontration.checkWolfWithEnemies(wolf);
		confrontration.checkWolfWithOtherWolfs(wolf);
		isMoveAbilityAvaible(Wolf.MOVE_UNIT);

	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	/*
	This function is used when switch loop, which in moveObjects function, choose case: "Cock"
	Than the switch loop, which in this function, choose random direction for random selected cock and it will move to the direction
	Before end of this function, checked selected cock's position relative to enemiy(Hunter,Wolf) or hens.
	If the cock moves in danger distance of enemiy it is going to remove from cocks list
	If the cock moves less than 3 units from any hen Entitymanger class is going to create random hen or cock and it will added coks/hens list
	 */
	private void moveCock(int direction) {
		
		String directionName=objectDirection[direction];
		int cockIndex=chooseRandomCock.nextInt(cocks.size());
		Cock cock = cocks.get(cockIndex);
		EntitiyLocation oldLocation=new EntitiyLocation(cock.getX(), cock.getY());
		
		
		switch (directionName) {
		case "Right":
			if (cock != null) {
				cock.moveRight(Cock.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cock.getX(), cock.getY()));
				moveMessage=cockIndex+". cock move to right direction "+ Cock.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Left":
			if (cock != null) {
				cock.moveLeft(Cock.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cock.getX(), cock.getY()));
				moveMessage=cockIndex+". cock move to left direction "+ Cock.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Up":
			if (cock != null) {
				cock.moveUp(Cock.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cock.getX(), cock.getY()));
				moveMessage=cockIndex+". cock move to up direction "+ Cock.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Down":
			if (cock != null) {
				cock.moveDown(Cock.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cock.getX(), cock.getY()));
				moveMessage=cockIndex+". cock  move to down direction "+ Cock.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		}
		confrontration.checkCockWithEnemies(cock);
		confrontration.checkCockWithOtherHens(cock);
		isMoveAbilityAvaible(Cock.MOVE_UNIT);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	/*
	This function is used when switch loop, which in moveObjects function, choose case: "Lion"
	Than the switch loop, which in this function, choose random direction for random selected lion and it will move to the direction
	Before end of this function, checked selected lion's position relative to enemiy(Hunter) or other lions.
	If the lion moves within 5 units of any of its prey, that animal is will removed its list
	If the lion moves in danger distance of any enemiy it is going to remove from lions list
	If the lion moves less than 3 units from any lion Entitymanger class is going to create random lion and it will added lions list
	 */
	private void moveLion(int direction) {
		
		String directionName=objectDirection[direction];
		int lionIndex=chooseRandomLion.nextInt(lions.size());
		Lion lion = lions.get(lionIndex);
		EntitiyLocation oldLocation=new EntitiyLocation(lion.getX(), lion.getY());
		
		switch (directionName) {
		case "Right":

			if (lion != null) {
				lion.moveRight(Lion.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(lion.getX(), lion.getY()));
				moveMessage=lionIndex+". lion move to right direction "+ Lion.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Left":
			if (lion != null) {
				lion.moveLeft(Lion.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(lion.getX(), lion.getY()));
				moveMessage=lionIndex+". lion move to left direction "+ Lion.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Up":
			if (lion != null) {
				lion.moveUp(Lion.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(lion.getX(), lion.getY()));
				moveMessage=lionIndex+". lion up to up direction "+ Lion.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		case "Down":
			if (lion != null) {
				lion.moveDown(Lion.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(lion.getX(), lion.getY()));
				moveMessage=lionIndex+". lion move to down direction "+ Lion.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}

			break;
		}
		
		confrontration.checkLionWithEnemies(lion);
		confrontration.checkLionWithOtherCows(lion, lionIndex);
		confrontration.checkLionWithOtherLions(lion);
		confrontration.checkLionWithOtherSheeps(lion, lionIndex);
		isMoveAbilityAvaible(Lion.MOVE_UNIT);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	/*
	This function is used when switch loop, which in moveObjects function, choose case: "Hen"
	Than the switch loop, which in this function, choose random direction for random selected hen and it will move to the direction
	Before end of this function, checked selected hen's position relative to enemiy(Hunter,Wolf) or coks.
	Ýf the hen moves in danger distance of any enemiy it is going to remove from hens list
	If the hen moves less than 3 units from any cock Entitymanger class is going to create random cock or hen and it will added hens/cocks list
	 */
	private void moveHen(int direction) {

		String directionName=objectDirection[direction];
		int henIndex=chooseRandomHen.nextInt(hens.size());
		Hen hen = hens.get(henIndex);

		EntitiyLocation oldLocation=new EntitiyLocation(hen.getX(), hen.getY());
		
		switch (directionName) {
		case "Right":

			if (hen != null) {
				hen.moveRight(Hen.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(hen.getX(), hen.getY()));
				moveMessage=henIndex+".hen move to right direction "+ Hen.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Left":
			if (hen != null) {
				hen.moveLeft(Hen.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(hen.getX(), hen.getY()));
				moveMessage=henIndex+".hen move to right direction "+ Hen.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Up":
			if (hen != null) {
				hen.moveUp(Hen.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(hen.getX(), hen.getY()));
				moveMessage=henIndex+". hen move to up direction "+ Hen.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Down":
			if (hen != null) {
				hen.moveDown(Hen.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(hen.getX(), hen.getY()));
				moveMessage=henIndex+". hen move to down direction "+ Hen.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		}
		
		confrontration.checkHenWithOtherCocks(hen);
		confrontration.checkHenWithEnemies(hen);
		isMoveAbilityAvaible(Hen.MOVE_UNIT);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	/*
	This function is used when switch loop, which in moveObjects function, choose case: "Cow"
	Than the switch loop, which in this function, choose random direction for random selected cow and it will move to the direction
	Before end of this function, checked selected cow's position relative to enemiy(Hunter,Wolf,Lion) or other cows.
	Ýf the cow moves in danger distance of on any enemiy it is going to remove from cows list
	If the cow moves less than 3 units from any cow Entitymanger class is going to create random cow  and it will added cows list
	 */
	private void moveCow(int direction) {
		
		String directionName=objectDirection[direction];
		int cowIndex=chooseRandomCow.nextInt(cows.size());
		Cow cow = cows.get(cowIndex);
		EntitiyLocation oldLocation=new EntitiyLocation(cow.getX(), cow.getY());
		
		switch (directionName) {
		case "Right":

			if (cow != null) {
				cow.moveRight(Cow.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cow.getX(), cow.getY()));
				moveMessage=cowIndex+". cow move to right direction "+ Cow.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Left":
			if (cow != null) {
				cow.moveLeft(Cow.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cow.getX(), cow.getY()));
				moveMessage=cowIndex+".cow move to left direction "+ Cow.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Up":
			if (cow != null) {
				cow.moveUp(Cow.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cow.getX(), cow.getY()));
				moveMessage=cowIndex+".cow move to up direction "+ Cow.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		case "Down":
			if (cow != null) {
				cow.moveDown(Cow.MOVE_UNIT);
				entityManager.updateLocation(oldLocation, new EntitiyLocation(cow.getX(), cow.getY()));
				moveMessage=cowIndex+".cow move to down direction "+ Cow.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			}
			break;
		}
		confrontration.checkCowWithEnemies(cow);
		confrontration.checkCowWithOtherCows(cow);
		isMoveAbilityAvaible(Cow.MOVE_UNIT);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	/*
	This function is used when switch loop, which in moveObjects function, choose case: "Hunter"
	Than the switch loop, which in this function, choose random direction for random selected hunter and it will move to the direction
	Before end of this function, checked selected hunter's position relative to other animals
	If the lion moves within 8 units of any of its prey, that animal is will removed its list
	 */
	private void moveHunter(int direction) {
		
		
		String directionName=objectDirection[direction];
		EntitiyLocation oldLocation=new EntitiyLocation(hunter.getX(), hunter.getY());
		switch (directionName) {
		case "Right":
			hunter.moveRight(Hunter.MOVE_UNIT);
			entityManager.updateLocation(oldLocation, new EntitiyLocation(hunter.getX(), hunter.getY()));
			moveMessage="Hunter move to right direction "+ Hunter.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			break;
		case "Left":
			
			hunter.moveLeft(Hunter.MOVE_UNIT);
			entityManager.updateLocation(oldLocation, new EntitiyLocation(hunter.getX(), hunter.getY()));
			moveMessage="Hunter move to left direction "+ Hunter.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			break;
		case "Up":
			
			hunter.moveUp(Hunter.MOVE_UNIT);
			entityManager.updateLocation(oldLocation, new EntitiyLocation(hunter.getX(), hunter.getY()));
			moveMessage="Hunter move to up direction "+ Hunter.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			break;
		case "Down":
			
			hunter.moveDown(Hunter.MOVE_UNIT);
			entityManager.updateLocation(oldLocation, new EntitiyLocation(hunter.getX(), hunter.getY()));
			moveMessage="Hunter move to down direction "+ Hunter.MOVE_UNIT+ " unit - Total move unit : "+ totalMoveUnit;
			break;
		}
		confrontration.checkHunterWithOthers();
		isMoveAbilityAvaible(Hunter.MOVE_UNIT);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	//This function check the total move unit whether it is less than 1000 unit or not
	private boolean isMoveAbilityAvaible(int moveUnit) {

		if (totalMoveUnit >= 0 && totalMoveUnit <= 1000) {
			moveAbilityIsAvaible = true;
			totalMoveUnit += moveUnit;
		} else {
			moveAbilityIsAvaible = false;
		}
		return moveAbilityIsAvaible;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	public boolean isMoveAbilityIsAvaible() {
		
		return moveAbilityIsAvaible;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	public int getTotalMoveUnit() {
		return totalMoveUnit;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	//This function gets animals count from EntityManager.class and return it
	public void shownEntitiesTotalCount() {
		
		System.out.println(entityManager.getTotalEntitieCount());
		
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void showMoveMessage() {
		System.out.println(moveMessage);
	}
	
	



}
