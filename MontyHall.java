package for_Project3;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MontyHall extends Game {
    private Random random;
    private int correctDoor;
    private int hostDoor;
    private int userFirstChoice;
    private int userSecondChoice;
    private boolean doorChosen;

    public MontyHall() {
    	super();
        this.random = new Random();
        this.correctDoor= 1 + random.nextInt(3);
        this.doorChosen = false;
    }

    @Override
    public String explainRules() {
        return "Welcome to the Monty Hall Problem!\n" +
               "You are on a game show with 3 doors.\n" +
               "Behind one door is a prize. Behind the other two are goats.\n" +
               "You pick a door, then the host opens one of the remaining doors, revealing a goat.\n" +
               "You then decide whether to stay with your original choice or switch to the other door.\n" +
               "If you pick the door with the prize, you win!\n";
    }

    @Override
    public String setup() {
    	if(!doorChosen) {
        	return "Please select door 1, 2, or 3.";
    	}else {
    		return "Would you like to change your door selection? If so, please enter the new number. If not, re-enter your first number.";
    	}
    }

    @Override
    public boolean goodPlayerInput(String guess) {
    	if(!doorChosen) {
	    	boolean good = guess.equals("1") || guess.equals("2") || guess.equals("3");
	    	if(good) {
	    		userFirstChoice = Integer.valueOf(guess);
	    	}
	    	return good;
    	}else {
    		boolean good = guess.equals("1") || guess.equals("2") || guess.equals("3");
	    	if(good) {
	    		userSecondChoice = Integer.valueOf(guess);
	    	}
	    	return good;
    	}
    }
    
    
    
    
    @Override
    public String checkWinOrLose() {
    	String toReturn = "";
    	if(!doorChosen) {
    		hostDoor = 1 + random.nextInt(3);    		
    		toReturn += "You have selected door number " + userFirstChoice + ".\n";
	    	toReturn += "I will now open one of the two remaining doors that do not have the prize.\n";
	    	while (hostDoor == correctDoor || hostDoor == userFirstChoice) {
	    		hostDoor = 1 + random.nextInt(3);
	    	}
	    	toReturn += "I have opened door number " + hostDoor + " to show that it is not the correct door.\n";
    		return toReturn;
    	}else {
    		toReturn = doorArt(correctDoor);
    		System.out.println(toReturn);
        	if (userSecondChoice == correctDoor && userSecondChoice != userFirstChoice) {
        		return "Congratulations, you won! Good choice to switch doors.";
        	} else if (userSecondChoice == correctDoor && userSecondChoice == userFirstChoice) {
        		return "Congratulations, you won! Good choice not to switch doors.";
        	} else if (userSecondChoice != correctDoor && userSecondChoice != userFirstChoice) {
        		return "You lost.. Probably shouldn't have switched doors LOL";
        	} else {
        		return "You lost.. Probably should've switched doors LOL";
        	}
    	}

    }

    @Override
    public boolean canPlayAgain() {
    	if(!doorChosen) {
    		doorChosen = true;
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public String doorArt(int doorIndex) {
        File file = new File("doors.txt");
        String[] doorOne = new String[7];
        String[] doorTwo = new String[7];
        String[] doorThree = new String[7];

        try {
            Scanner scan = new Scanner(file);
            for (int i = 0; i < 7; i++) {
                if (scan.hasNextLine()) {
                    doorOne[i] = scan.nextLine();
                }
            }

            for (int i = 0; i < 7; i++) {
                if (scan.hasNextLine()) {
                    doorTwo[i] = scan.nextLine();
                }
            }

            for (int i = 0; i < 7; i++) {
                if (scan.hasNextLine()) {
                    doorThree[i] = scan.nextLine();
                }
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "File not found!";
        }

        switch (doorIndex) {
            case 3:
                return String.join("\n", doorOne);
            case 2:
                return String.join("\n", doorTwo);
            case 1:
                return String.join("\n", doorThree);
            default:
                return "Invalid door index!";
        }
    }

}


