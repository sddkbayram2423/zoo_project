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
import tools.Gender;
/**
 * 
 * This class was created to manage all entites(to create,to remove,to add)
 * 
 * @author Sýddýk Bayram
 */
public class EntityManager {
	
	// Variables
	private static final int WIDTH = 500;
	
	private static EntityManager entityManager;
	
	private ArrayList<EntitiyLocation> usedLocations;

	private ArrayList<Wolf> wolfs;

	private ArrayList<Lion> lions;

	private ArrayList<Cock> cocks;

	private ArrayList<Cow> cows;

	private ArrayList<Sheep> sheeps;

	private ArrayList<Hen> hens;
	
	private Hunter hunter;
	
	//--------------------------------------------------------------------------------------------------------------
	
	//Random counts for location and gender
	private Random randomIntForXLocation;
	
	private Random randomIntForYLocation;

	private Random randomIntRandomForGender;
	
	//--------------------------------------------------------------------------------------------------------------
	
	// Count of entites
	private int maleWolfCount = 0;
	
	private int femaleWolfCount = 0;
	
	private int maleSheepCount = 0;
	
	private int femaleSheepCount = 0;
	
	private int maleCowCount = 0;
	
	private int femaleCowCount = 0;
	
	private int cockCount = 0;
	
	private int henCount = 0;
	
	private int maleLionCount = 0;
	
	private int femaleLionCount = 0;
	
	private int hunterCount = 0;
	
	//--------------------------------------------------------------------------------------------------------------
	

	//Consturctor
	private EntityManager() {
		
		randomIntRandomForGender = new Random();
		
		randomIntForXLocation = new Random();
		
		randomIntForYLocation = new Random();
		
		usedLocations=new ArrayList<>();

		lions = new ArrayList<>();
		
		wolfs = new ArrayList<>();
		
		cocks = new ArrayList<>();
		
		cows = new ArrayList<>();
		
		sheeps = new ArrayList<>();
		
		hens = new ArrayList<>();
		
		
		addAllWolfs();
		addAllCocks();
		addAllCows();
		addAllLions();
		addHunter();
		addAllHens();
		addAllSheeps();
		
		System.out.println("-------------At Beginning Entites Count----------");
		System.out.println("Sheep Count: " + sheeps.size());
		System.out.println("Wolf Count : " + wolfs.size());
		System.out.println("Cock Count : " + cocks.size());
		System.out.println("Cow Count  : " + cows.size());
		System.out.println("Lion Count : " + lions.size());
		System.out.println("Hen Count  : " + hens.size());
		System.out.println("-------------------------------------------------\n");

	}
	//--------------------------------------------------------------------------------------------------------------
	
	//Adding function  to add  10 wolfs by creating random location
	private void addAllWolfs() {

		Wolf wolf = null;

		for (int i = 0; i < 10; i++) {

			EntitiyLocation location = getNewRandomLocation();

			if (i % 2 == 0) {
				wolf = new Wolf(location.getX(), location.getY(), Gender.FEMALE);
				femaleWolfCount++;
			} else {
				wolf = new Wolf(location.getX(), location.getY(), Gender.MALE);
				maleWolfCount++;
			}
			wolfs.add(wolf);
			usedLocations.add(location);
		}

	}
	//--------------------------------------------------------------------------------------------------------------

	
	//Adding function  to add  8 lions by creating random location
	private void addAllLions() {

		Lion lion = null;

		for (int i = 0; i < 8; i++) {

			EntitiyLocation location = getNewRandomLocation();

			if (i % 2 == 0) {
				lion = new Lion(location.getX(), location.getY(), Gender.FEMALE);
				femaleLionCount++;
			} else {
				lion = new Lion(location.getX(), location.getY(), Gender.MALE);
				maleLionCount++;

			}
			lions.add(lion);
			usedLocations.add(location);
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	

	//Adding function  to add  10 cocks by creating random location
	private void addAllCocks() {

		Cock cock = null;

		for (int i = 0; i < 10; i++) {

			EntitiyLocation location =getNewRandomLocation();
			cockCount++;
			cock = new Cock(location.getX(), location.getY());
			cocks.add(cock);
			usedLocations.add(location);
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	

	//Adding function  to add  10 cows by creating random location
	private void addAllCows() {

		Cow cow = null;
		for (int i = 0; i < 10; i++) {

			EntitiyLocation location = getNewRandomLocation();

			if (i % 2 == 0) {
				cow = new Cow(location.getX(), location.getY(), Gender.FEMALE);
				femaleCowCount++;
			} else {
				cow = new Cow(location.getX(), location.getY(), Gender.MALE);
				maleCowCount++;
			}
			cows.add(cow);
			usedLocations.add(location);
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Adding function  to add  30 sheeps by creating random location
	private void addAllSheeps() {

		Sheep sheep = null;

		for (int i = 0; i < 30; i++) {
			

			EntitiyLocation location = getNewRandomLocation();

			if (i % 2 == 0) {
				sheep = new Sheep(location.getX(), location.getY(), Gender.FEMALE);
				femaleSheepCount++;
			} else {
				sheep = new Sheep(location.getX(), location.getY(), Gender.MALE);
				maleSheepCount++;

			}
			sheeps.add(sheep);
			usedLocations.add(location);
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Adding function  to add  10 hens by creating random location
	private void addAllHens() {
		Hen hen = null;
		for (int i = 0; i < 10; i++) {
			EntitiyLocation location = getNewRandomLocation();
			hen = new Hen(location.getX(), location.getY());
			henCount++;
			hens.add(hen);
			usedLocations.add(location);
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Adding function  to add hunter by creating random location
	private Hunter addHunter() {

		EntitiyLocation location = getNewRandomLocation();
		hunter = new Hunter(location.getX(), location.getY());
		usedLocations.add(location);
		hunterCount = 1;

		return hunter;
	}
	//--------------------------------------------------------------------------------------------------------------

	
	//Delete function  to remove cow
	public void removeCow(Cow cow) {

		if (cow.getGender() == Gender.FEMALE) {

			if (femaleCowCount <= 0) {
				femaleCowCount = 0;
			} else {
				femaleCowCount--;
			}

		} else {

			if (maleCowCount <= 0) {
				maleCowCount = 0;
			} else {
				maleCowCount--;
			}

		}
		cows.remove(new Cow(cow.getX(), cow.getY(), cow.getGender()));
		usedLocations.add(new EntitiyLocation(cow.getX(), cow.getY()));
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Delete function  to remove cock
	public void removeCock(Cock cock) {

		if (cockCount <= 0) {
			cockCount = 0;
		} else {
			cockCount--;
		}
		cocks.remove(new Cock(cock.getX(), cock.getY()));
		usedLocations.add(new EntitiyLocation(cock.getX(), cock.getY()));
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Delete function  to remove hen
	public void removeHen(Hen hen) {
		if (henCount <= 0) {
			henCount = 0;
		} else {
			henCount--;
		}
		hens.remove(new Hen(hen.getX(), hen.getY()));
		usedLocations.remove(new EntitiyLocation(hen.getX(), hen.getY()));
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Delete function  to remove lion
	public void removeLion(Lion lion) {

		if (lion.getGender() == Gender.FEMALE) {
			if (femaleLionCount <= 0) {
				femaleLionCount = 0;
			} else {
				femaleLionCount--;
			}

		} else {
			if (maleLionCount <= 0) {
				maleLionCount = 0;
			} else {
				maleLionCount--;
			}
		}
		lions.remove(new Lion(lion.getX(), lion.getY(), lion.getGender()));
		usedLocations.remove(new EntitiyLocation(lion.getX(), lion.getY()));
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Delete function  to remove wolf
	public void removeWolf(Wolf wolf) {

		if (wolf.getGender() == Gender.FEMALE) {

			if (maleLionCount <= 0) {
				femaleWolfCount = 0;
			} else {
				femaleWolfCount--;
			}

		} else {

			if (maleWolfCount <= 0) {
				maleWolfCount = 0;
			} else {
				maleWolfCount--;
			}
		}

		wolfs.remove(new Wolf(wolf.getX(), wolf.getY(), wolf.getGender()));
		usedLocations.remove(new EntitiyLocation(wolf.getX(), wolf.getY()));
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Delete function  to remove sheep
	public void removeSheep(Sheep sheep) {

		if (sheep.getGender() == Gender.FEMALE) {

			if (femaleSheepCount <= 0) {
				femaleSheepCount = 0;
			} else {
				femaleSheepCount--;
			}
		} else {

			if (maleSheepCount <= 0) {
				maleSheepCount = 0;
			} else {
				maleSheepCount--;
			}

		}
		sheeps.remove(new Sheep(sheep.getX(), sheep.getY(), sheep.getGender()));
		usedLocations.remove(new EntitiyLocation(sheep.getX(), sheep.getY()));
	}
	//--------------------------------------------------------------------------------------------------------------
	

	//Function to generate a random hen or cock when a rooster and hen confronration
	public void createRandomCockOrHen() {
		

		// 0=Cock 1=Hen
		int randomForGender = randomIntRandomForGender.nextInt(2);
		
		
		EntitiyLocation location = getNewRandomLocation();
		
		if (randomForGender == 0) {
			Cock cock = new Cock(location.getX(), location.getY());
			cocks.add(cock);
			cockCount++;
			usedLocations.add(location);
			System.err.println("Random a cock created");
			
		} 
		if(randomForGender==1) {
			Hen hen = new Hen(location.getX(), location.getY());
			hens.add(hen);
			henCount++;
			usedLocations.add(location);
			System.err.println("Random a hen created");
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Function to generate a random male or female cow when different cows confronration
	public void createRandomCow() {

		// 0=Male 1=Female
		int randomForGender = randomIntRandomForGender.nextInt(2);
		EntitiyLocation location = getNewRandomLocation();
		Cow cow = null;
		if (randomForGender == 0) {
			cow = new Cow(location.getX(), location.getY(), Gender.MALE);
			System.err.println("Random a male cow created");
			maleCowCount++;
		} else {
			cow = new Cow(location.getX(), location.getY(), Gender.FEMALE);
			System.err.println("Random a female cow created");
			femaleCowCount++;
		}
		cows.add(cow);
		usedLocations.add(location);
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Function to generate a random male or female lion when different cows confronration
	public void createRandomLion() {

		// 0=Male 1=Female
		int randomForGender = randomIntRandomForGender.nextInt(2);
		Lion lion = null;
		EntitiyLocation location = getNewRandomLocation();

		if (randomForGender == 0) {
			lion = new Lion(location.getX(), location.getY(), Gender.MALE);
			System.err.println("Random a male lion created");
			maleLionCount++;
		} else {
			lion = new Lion(location.getX(), location.getY(), Gender.FEMALE);
			System.err.println("Random a female lion created");
			femaleCowCount++;
		}
		lions.add(lion);
		usedLocations.add(location);
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Function to generate a random male or female sheep when different cows confronration
	public void createRandomSheep() {

		
		// 0=Male 1=Female
		int randomForGender = randomIntRandomForGender.nextInt(2);
		Sheep sheep = null;
		EntitiyLocation location = getNewRandomLocation();
		if (randomForGender == 0) {
			sheep = new Sheep(location.getX(), location.getY(), Gender.MALE);
			System.err.println("Random a male sheep created");
			maleSheepCount++;
		} else {
			sheep = new Sheep(location.getX(), location.getY(), Gender.FEMALE);
			System.err.println("Random a female sheep created");
			femaleSheepCount++;
		}

		sheeps.add(sheep);
		usedLocations.add(location);
	}
	//--------------------------------------------------------------------------------------------------------------
	
	//Function to generate a random male or female sheep wolf different cows confronration
	public void createRandomWolf() {

		// 0=Male 1=Female
		int randomForGender = randomIntRandomForGender.nextInt(2);
		Wolf wolf = null;
		EntitiyLocation location = getNewRandomLocation();

		if (randomForGender == 0) {
			wolf = new Wolf(location.getX(), location.getY(), Gender.MALE);
			System.err.println("Random a male wolf created");
			maleWolfCount++;
		} else {
			wolf = new Wolf(location.getX(), location.getY(), Gender.FEMALE);
			System.err.println("Random a female wolf created");
			femaleWolfCount++;
		}
		wolfs.add(wolf);
		usedLocations.add(location);

	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//Function to generate a random new location for entities
	public EntitiyLocation getNewRandomLocation() {
		
		EntitiyLocation location = new EntitiyLocation(randomIntForXLocation.nextInt(WIDTH),
				randomIntForYLocation.nextInt(WIDTH));

		while (isLocationUsed(location)) {
			location = new EntitiyLocation(randomIntForXLocation.nextInt(WIDTH), randomIntForYLocation.nextInt(WIDTH));
		}
		return location;
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//The function that checks whether the same position exists or not (new Location(x,y))
	public EntitiyLocation getNewLocation(int x, int y) {

		EntitiyLocation location = new EntitiyLocation(x, y);

		while (isLocationUsed(location)) {
			location = new EntitiyLocation(randomIntForXLocation.nextInt(WIDTH), randomIntForYLocation.nextInt(WIDTH));
		}
		return location;
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	//The Function that checks whether the position is used or not
	public boolean isLocationUsed(EntitiyLocation location) {

		for (EntitiyLocation entitiyLocation : usedLocations) {
			if (location.getX() == entitiyLocation.getX() && location.getY() == entitiyLocation.getY()) {
				return true;
			}
		}
		return false;

	}
	//--------------------------------------------------------------------------------------------------------------
	
	
	public void updateLocation(EntitiyLocation oldLocation,EntitiyLocation newLocation) {
			usedLocations.remove(oldLocation);
			usedLocations.add(newLocation);
	}
	
	//--------------------------------------------------------------------------------------------------------------

	
	// This function return singelton object of entityManager class
	public static EntityManager getIntanceEntityManager() {

		if (entityManager == null) {
			entityManager = new EntityManager();
		}
		return entityManager;
	}
	
	//--------------------------------------------------------------------------------------------------------------
	
	
	// This function return count of entites aggording to the gender
	public String getTotalEntitieCount() {
		
		int total=femaleCowCount +maleCowCount+femaleLionCount+maleLionCount+femaleSheepCount+maleSheepCount+femaleWolfCount+maleWolfCount+cockCount+henCount+henCount;

		String totalEntitieCount = "\nFemale Cow Count  : " + femaleCowCount + "\nMale Cow Count    : " + maleCowCount
				+ "\nFemale Sheep Count: " + femaleSheepCount + "\nMale Sheep Count  : " + maleSheepCount
				+ "\nFemale Lion Count : " + femaleLionCount + "\nMale Lion Count   : " + maleLionCount
				+ "\nFemale Wolf Count : " + femaleWolfCount + "\nMale Wolf Count   : " + maleWolfCount
				+ "\nCock Count        : " + cockCount + "\nHen Count         : " + henCount + "\nHunter Count      : "
				+ hunterCount+"\nTotal      : "+ total;

		return totalEntitieCount;
	}
	//---------------------------------------------------Setter/getter---------------------------------------------


	public ArrayList<Wolf> getWolfs() {
		return wolfs;
	}

	public ArrayList<Lion> getLions() {
		return lions;
	}

	public ArrayList<Cock> getCocks() {
		return cocks;
	}

	public ArrayList<Cow> getCows() {
		return cows;
	}

	public ArrayList<Sheep> getSheeps() {
		return sheeps;
	}

	public ArrayList<Hen> getHens() {
		return hens;
	}

	public Hunter getHunter() {

		return hunter;
	}


}


