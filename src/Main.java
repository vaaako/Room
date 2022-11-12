package room;

public class Main {
	public static void main(String[] args) {
		String choice;

		// Make a Actions for each
		Actions actions = new Actions(); // Get callByName method

		GameLogic.showInstructions();
		GameLogic.anythingToContinue();
		GameLogic.clearConsole(); // Clear console after each choice

		Actions.showStart();
		GameLogic.anythingToContinue();
		
		while(GameLogic.running) {
			// DEBUG
			// System.out.println("doorIsOpen: "+ Actions.doorIsOpen);
			// System.out.println("keyNearEye: "+ Actions.keyNearEye);
			// System.out.println("haveKey: "+ Actions.haveKey);
			// System.out.println("snailIsSmashed: "+ Actions.snailIsSmashed);

			// System.out.println("\ncubeFinal: "+ Actions.cubeFinal);
			// System.out.println("doorFinal: "+ Actions.doorFinal);
			// System.out.println("lookedUp: "+ Actions.lookedUp);

			// Make dynamic with the changes
			Actions.checkAround(); // Resume

			choice = GameLogic.readInput("\nO que você deseja fazer? \n[ > ] ");
			choice = GameLogic.checkAction(choice);

			GameLogic.clearConsole();

			// Eye
			// Other actions, same result
			if(GameLogic.stringContains(choice, "touchEye", "attackEye")) // Throw is use
				Actions.checkEye();


			// Cube
			else if(choice.equals("attackCube"))
				Actions.touchCube();

			else if(choice.equals("check"))
				Actions.checkAround();

			else {
				boolean action = actions.callByName(choice); // Call method
				if(!action) // If method don't exist
					System.out.println("Ação impossível");
			}

			GameLogic.anythingToContinue();
		}

		// End of the loop (I break the loop, it's symbolical)
		GameLogic.clearConsole();
		Actions.granFinale();
	}
}
