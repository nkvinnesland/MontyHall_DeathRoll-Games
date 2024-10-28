package for_Project3;

import java.util.Random;

public class DeathRoll extends Game {
	
	private int maxRoll;
	private int currentRoll;
	private boolean userTurn;
	private Random rand = new Random();
	
	public DeathRoll() {
		super();
		userTurn = true;
		maxRoll = 100;
	}

	@Override
	public String setup() {
		return "Please enter any key or just press enter to roll.";
	}

	@Override
	public String checkWinOrLose() {
		String diceArt = getDiceArt(currentRoll);
		if (currentRoll == 1) {
			if (userTurn) {
				return "\nComputer loses!";
			} else {
				return "\nUser loses!";
			}
		}
		return "... and the game continues...";
	}

	@Override
	public boolean canPlayAgain() {
		if(currentRoll == 1) {
			maxRoll = 100;
			System.out.println("The game will restart now... Enter quit to exit at anytime.");
			return true;
		} else {
		return true;
		}
	}

	@Override
	public String explainRules() {
		return "Welcome to Death Roll! The most infuriating gambling game known to man!\n"
				+ "The rules are very simple... the game starts with a one-hundred sided die that the user will roll.\n"
				+ "Depending on what the user rolls, the computer will then roll a die with that many sides.\n"
				+ "Whoever rolls a 1 first loses.";
	}

	@Override
	public boolean goodPlayerInput(String guess) {

		currentRoll = rand.nextInt(maxRoll) + 1;
		maxRoll = currentRoll;
		userTurn = !userTurn;
		String diceArt = getDiceArt(currentRoll);
		if(currentRoll != 1) {
			System.out.println(diceArt + "\n" + (userTurn ? "Computer" : "User") + " rolled a " + currentRoll + ". Now it's " + (userTurn ? "User's" : "Computer's") + " turn.");
		} else {
			System.out.println(diceArt + "\n" + (userTurn ? "Computer" : "User") + " rolled a " + currentRoll + ". Game Over.");
		}
		return true;
	}
	
	private String getDiceArt(int roll) {
		String[] diceArt = {
			"+-------+\n|       |\n|   *   |\n|       |\n+-------+",
			"+-------+\n| *     |\n|       |\n|     * |\n+-------+",
			"+-------+\n| *     |\n|   *   |\n|     * |\n+-------+",
			"+-------+\n| *   * |\n|       |\n| *   * |\n+-------+",
			"+-------+\n| *   * |\n|   *   |\n| *   * |\n+-------+",
			"+-------+\n| *   * |\n| *   * |\n| *   * |\n+-------+"   
		};

		// This just shows a die 1-6 representation of the actual die... showing a 53 sided die is too complicated for my tiny brain
		int index = (roll - 1) % 6;
		return diceArt[index];
	}
}







