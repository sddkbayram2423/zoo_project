package manager;

import java.util.ArrayList;

import entities.Cock;
import entities.Cow;
import entities.Hen;
import entities.Hunter;
import entities.Lion;
import entities.Sheep;
import entities.Species;
import entities.Wolf;

/**
 * 
 * This class was created to control the position of Entites relative to each
 * other.
 * 
 * @author Sýddýk Bayram
 */
public class ConfrontrationCheckManager {

	// Variables
	private EntityManager entityManager;

	private Hunter hunter;

	private ArrayList<Wolf> wolfs;

	private ArrayList<Lion> lions;

	private ArrayList<Cock> cocks;

	private ArrayList<Cow> cows;

	private ArrayList<Sheep> sheeps;

	private ArrayList<Hen> hens;

	// --------------------------------------------------------------------------------------------------------------

	// Distances that assets should stay away from each other
	private final static int DANGER_DISTANCE_OF_WOLF = 4;

	private final static int DANGER_DISTANCE_OF_HUNTER = 8;

	private final static int DANGER_DISTANCE_OF_LION = 5;

	private final static int MATCHING_DISTANCE_OF_SAME_SPECIES_BUT_DIFFERENT_GENDER = 3;

	// --------------------------------------------------------------------------------------------------------------

	// Constructor
	public ConfrontrationCheckManager() {

		entityManager = EntityManager.getIntanceEntityManager();

		hunter = entityManager.getHunter();

		wolfs = entityManager.getWolfs();

		sheeps = entityManager.getSheeps();

		hens = entityManager.getHens();

		cocks = entityManager.getCocks();

		lions = entityManager.getLions();

		cows = entityManager.getCows();

	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of a wolf relative to sheeps.
	public void checkWolfWithOtherSheeps(Wolf wolf, int index) {

		for (Sheep sheep : sheeps) {

			if (checkConfrontrationWith(wolf, sheep, DANGER_DISTANCE_OF_WOLF)) {

				entityManager.removeSheep(sheep);
				System.err.println(index + ". wolf hunted a sheep");
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of a wolf relative to hens.
	public void checkWolfWithOtherHens(Wolf wolf, int index) {

		for (Hen hen : hens) {
			if (checkConfrontrationWith(wolf, hen, DANGER_DISTANCE_OF_WOLF)) {
				entityManager.removeHen(hen);
				System.err.println(index + ". wolf hunted a hen");
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of a wolf relative to coks.
	public void checkWolfWithOherCocks(Wolf wolf, int index) {

		for (Cock cock : cocks) {

			if (checkConfrontrationWith(wolf, cock, DANGER_DISTANCE_OF_WOLF)) {

				entityManager.removeCock(cock);
				System.err.println(index + ". wolf hunted a cock");
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of a lion relative to cows.
	public void checkLionWithOtherCows(Lion lion, int index) {

		for (Cow cow : cows) {
			if (checkConfrontrationWith(lion, cow, DANGER_DISTANCE_OF_LION)) {
				entityManager.removeCow(cow);
				System.err.println(index + ". lion hunted a cow");
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of a lion relative to sheeps.
	public void checkLionWithOtherSheeps(Lion lion, int index) {

		for (Sheep sheep : sheeps) {
			if (checkConfrontrationWith(lion, sheep, DANGER_DISTANCE_OF_LION)) {
				entityManager.removeSheep(sheep);
				System.err.println(index + ". lion hunted a sheep");
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of a wolf relative to enemey(Hunter).
	public void checkWolfWithEnemies(Wolf wolf) {

		if (checkConfrontrationWith(hunter, wolf, DANGER_DISTANCE_OF_HUNTER)) {
			entityManager.removeWolf(wolf);
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of a lion relative to enemey(Hunter).
	public void checkLionWithEnemies(Lion lion) {

		if (checkConfrontrationWith(hunter, lion, DANGER_DISTANCE_OF_HUNTER)) {
			entityManager.removeLion(lion);
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of the hunter relative to other entities.
	public void checkHunterWithOthers() {

		for (Sheep sheep : sheeps) {
			if (checkConfrontrationWith(hunter, sheep, DANGER_DISTANCE_OF_HUNTER)) {
				entityManager.removeSheep(sheep);
				System.err.println("Hunter hunted a sheep");
			}
		}

		for (Hen hen : hens) {
			if (checkConfrontrationWith(hunter, hen, DANGER_DISTANCE_OF_HUNTER)) {
				entityManager.removeHen(hen);
				System.err.println("Hunter hunted a hen");
			}
		}

		for (Cock cock : cocks) {
			if (checkConfrontrationWith(hunter, cock, DANGER_DISTANCE_OF_HUNTER)) {
				entityManager.removeCock(cock);
				System.err.println("Hunter hunted a cock");
			}
		}
		for (Cow cow : cows) {
			if (checkConfrontrationWith(hunter, cow, DANGER_DISTANCE_OF_HUNTER)) {
				entityManager.removeCow(cow);
				System.err.println("Hunter hunted a cow");
			}
		}
		for (Lion lion : lions) {

			if (checkConfrontrationWith(hunter, lion, DANGER_DISTANCE_OF_HUNTER)) {

				entityManager.removeLion(lion);
				System.err.println("Hunter hunted a lion");
			}
		}
		for (Wolf wolf : wolfs) {

			if (checkConfrontrationWith(hunter, wolf, DANGER_DISTANCE_OF_HUNTER)) {
				entityManager.removeWolf(wolf);
				System.err.println("Hunter hunted a wolf");
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of the hen relative to cocks
	public void checkHenWithOtherCocks(Hen hen) {

		int countConfrontration = 0;

		for (Cock cock : cocks) {
			if (checkConfrontrationWith(cock, hen, MATCHING_DISTANCE_OF_SAME_SPECIES_BUT_DIFFERENT_GENDER)) {

				countConfrontration++;

			}
		}

		if (countConfrontration >= 0) {

			for (int i = 0; i < countConfrontration; i++) {
				entityManager.createRandomCockOrHen();
			}

		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks the distance of the cokc relative to hens
	public void checkCockWithOtherHens(Cock cock) {

		int countConfrontration = 0;

		for (Hen hen : hens) {
			if (checkConfrontrationWith(cock, hen, MATCHING_DISTANCE_OF_SAME_SPECIES_BUT_DIFFERENT_GENDER)) {
				countConfrontration++;

			}
		}

		if (countConfrontration >= 0) {
			for (int i = 0; i < countConfrontration; i++) {
				entityManager.createRandomCockOrHen();
			}

		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a lion's distance from other lions.
	public void checkLionWithOtherLions(Lion lion) {

		int countConfrontration = 0;

		for (Lion lion1 : lions) {
			if (lion.getX() != lion1.getX() || lion.getY() != lion1.getY() && lion.getGender() != lion1.getGender()) {

				if (checkConfrontrationWith(lion, lion1, MATCHING_DISTANCE_OF_SAME_SPECIES_BUT_DIFFERENT_GENDER)) {
					countConfrontration++;
				}
			}

		}
		if (countConfrontration >= 0) {

			for (int i = 0; i < countConfrontration; i++) {
				entityManager.createRandomLion();
			}

		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a cow's distance from other cows.
	public void checkCowWithOtherCows(Cow cow) {
		int countConfrontration = 0;
		for (Cow cow1 : cows) {
			if (cow.getX() != cow1.getX() || cow.getY() != cow1.getY() && cow.getGender() != cow1.getGender()) {
				if (checkConfrontrationWith(cow, cow1, MATCHING_DISTANCE_OF_SAME_SPECIES_BUT_DIFFERENT_GENDER)) {
					countConfrontration++;
				}
			}

		}
		if (countConfrontration >= 0) {

			for (int i = 0; i < countConfrontration; i++) {
				entityManager.createRandomCow();
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a sheep's distance from other sheeps.
	public void checkSheepWithOtherSheeps(Sheep sheep) {

		int countConfrontration = 0;

		for (Sheep sheep1 : sheeps) {
			if (sheep.getX() != sheep1.getX()
					|| sheep.getY() != sheep1.getY() && sheep.getGender() != sheep1.getGender()) {
				if (checkConfrontrationWith(sheep, sheep1, MATCHING_DISTANCE_OF_SAME_SPECIES_BUT_DIFFERENT_GENDER)) {
					countConfrontration++;
				}
			}
		}
		if (countConfrontration >= 0) {

			for (int i = 0; i < countConfrontration; i++) {
				entityManager.createRandomSheep();
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a wolf's distance from other wolfs.
	public void checkWolfWithOtherWolfs(Wolf wolf) {

		int countConfrontration = 0;
		for (Wolf wolf1 : wolfs) {
			if (wolf.getX() != wolf1.getX() || wolf.getY() != wolf1.getY() && wolf.getGender() != wolf1.getGender()) {
				if (checkConfrontrationWith(wolf, wolf1, MATCHING_DISTANCE_OF_SAME_SPECIES_BUT_DIFFERENT_GENDER)) {

					countConfrontration++;
				}
			}

		}
		if (countConfrontration >= 0) {
			for (int i = 0; i < countConfrontration; i++) {
				entityManager.createRandomSheep();
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a sheep's distance from enemies(the hunter,lions,wolfs)
	public void checkSheepWithEnemies(Sheep sheep) {

		if (checkConfrontrationWith(sheep, hunter, DANGER_DISTANCE_OF_HUNTER)) {
			entityManager.removeSheep(sheep);
		}

		for (Lion lion : lions) {
			if (checkConfrontrationWith(sheep, lion, DANGER_DISTANCE_OF_LION)) {
				entityManager.removeSheep(sheep);
			}
		}
		for (Wolf wolf : wolfs) {
			if (checkConfrontrationWith(sheep, wolf, DANGER_DISTANCE_OF_LION)) {
				entityManager.removeSheep(sheep);
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a cow's distance from enemies(the hunter,lions)
	public void checkCowWithEnemies(Cow cow) {

		if (checkConfrontrationWith(cow, hunter, DANGER_DISTANCE_OF_HUNTER)) {
			entityManager.removeCow(cow);
		}

		for (Lion lion : lions) {
			if (checkConfrontrationWith(cow, lion, DANGER_DISTANCE_OF_WOLF)) {
				entityManager.removeCow(cow);
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a cock's distance from enemies(the hunter,lions)
	public void checkCockWithEnemies(Cock cock) {

		if (checkConfrontrationWith(cock, hunter, DANGER_DISTANCE_OF_HUNTER)) {
			entityManager.removeCock(cock);
		}

		for (Wolf wolf : wolfs) {
			if (checkConfrontrationWith(cock, wolf, DANGER_DISTANCE_OF_WOLF)) {
				entityManager.removeCock(cock);
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a hen's distance from enemies(the hunter,wolfs)
	public void checkHenWithEnemies(Hen hen) {

		if (checkConfrontrationWith(hen, hunter, DANGER_DISTANCE_OF_HUNTER)) {
			entityManager.removeHen(hen);
		}

		for (Wolf wolf : wolfs) {
			if (checkConfrontrationWith(hen, wolf, DANGER_DISTANCE_OF_WOLF)) {
				entityManager.removeHen(hen);
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------

	// This function checks a specie's distance from other specie theat should stay
	// away from each other
	private boolean checkConfrontrationWith(Species specie, Species specie2, int distance) {
		return Math.abs(specie.getX() - specie2.getX()) <= distance
				|| Math.abs(specie.getY() - specie2.getY()) <= distance;
	}
}
