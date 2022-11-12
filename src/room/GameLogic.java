package room;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.io.IOException;

public class GameLogic {
	private static Scanner scanner = new Scanner(System.in);
	
	// "Global" variables (Acessible from all classes)
	public static boolean running = true;

	public static void showInstructions() {
		printSeparator(60);
		slowPrint(
			"Bem vindo(a) ao Room\n"
			+ "Vou te explicar brevente as regras do jogo.\n\n"
			+ "Room é um text adventure, isso significa que todas as ações serão por texto, use sua imaginação.\n\n"
			+ "Funcionará assim: Eu, o narrador, explicarei sua situação e ao final você decidirá qual ação deseja tomar.\n"
			+ "Digite palavras como: \"Ir para\", \"Tocar no\" etc.\n\n"
			+ "Exemplo: \"Você está de frente para uma criatura que acabou de te fazer questionar sua existência, o que deseja fazer?\"\n"
			+ "[ > ] Quero esmagar a formiga\n\n"
			+ "Resumindo: Seja objetivo para que suas ações sejam bem interpretadas, tente usar \"Verbo\" + \"adjetivo\"\n"
			+ "Recomendo jogar de uma vez só, tendo em vista que não tem função de salvar progresso.\n"
			+ "Lembre-se de escrever apenas UMA ação por vez, para não estragar sua imersão. Bom jogo."
			, 10
		);
		printSeparator(60);
	}

	public static String readInput(String prompt) {
		System.out.print(prompt);
		String input = scanner.nextLine();
		return input;
	}

	public static boolean stringContains(String inputStr, String... items) {
		inputStr = inputStr.toLowerCase();

		for(String i: items)
			if(inputStr.contains(i))
				return true;
		return false;
	}


	// [[[], [], []], [[], [], []]]
	public static String checkAction(String toCheck) {
		String[][][] allTypesArray = {
			// Action Types
			{
				/* Check */  { "check", "andar", "aproximar", "checar", "chegar", "examinar", "ir ao", "ir ate", "ir até", "ir para", "observar", "olhar", "vasculhar", "ver" },
				/* Touch */  { "touch", "descobrir", "determinar", "examinar", "mexer", "tocar" },
				/* Attack */ { "attack", "acabar", "bater", "chutar", "destruir", "esmagar", "matar", "multilar", "pisar", "socar", "soco", "atacar" },
				/* Use */    { "use", "abrir", "arremessar", "atravessar", "colocar", "entrar", "jogar", "pegar", "por", "segurar", "utilizar", "usar", "passar", "sair" }
			},

			{
				/* Eye */    { "eye", "direita", "olho"  },
				/* Cube */   { "cube", "asbismo", "atras", "atrás", "costas", "cubo", "escuridão", "escuro", "quadrado", "void", "preto" },
				/* Snail */  { "snail", "animal", "caracol", "caramujo", "criatura", "esquerda", "eu", "ser", "snail", "mim" },
				/* Door */   { "door", "entrada", "frente", "passagem", "saida", "saída", "porta" },
				/* Up */     { "up", "acima", "cima", "lâmpada" },
				/* Around */ { "around", "ao redor", "em volta", "sala", "todas", "tudo" },
				/* Key */    { "key", "chave", "objeto de abrir portas" },
				/* Down */   { "down", "chão", "baixo" }

			}
		};

		toCheck = toCheck.toLowerCase();
		String actionType = "";
		for (int i=0; i<allTypesArray[0].length; i++) { // Check each array of types
			if(Arrays.stream(allTypesArray[0][i]).anyMatch(toCheck::contains)) { // If is a match
				actionType = allTypesArray[0][i][0]; // Return first item of matched array (First item is the array type)
				break;
			}
		}

		String toWhat = "";
		for (int i=0; i<allTypesArray[1].length; i++) {
			if(Arrays.stream(allTypesArray[1][i]).anyMatch(toCheck::contains)) {
				toWhat = allTypesArray[1][i][0];
				break;
			}
		}

		if(toWhat.length()>0) // String is not empty
			toWhat = toWhat.substring(0, 1).toUpperCase() + toWhat.substring(1); // First letter to upper case

		// return actionType + "-" + toWhat;
		return actionType + toWhat;
	}



	public static void clearConsole() {
		for(int i=0; i<100; i++)
			System.out.println();
	}

	public static void printSeparator(int n) {
		System.out.println();
		for(int i=0; i<n; i++)
			System.out.print("-");
		System.out.println();
	}

	public static void printHeading(String title) {
		printSeparator(60);
		System.out.println(title);
		printSeparator(60);
	}

	public static void anythingToContinue() {
		System.out.print("\n\nEnter to continue: ");
		scanner.nextLine(); // Clear scanner
		System.out.println();
	}

	public static void slowPrint(String message, int time) {
		char[] chars = message.toCharArray();

		// Print a char from the array, then sleep for 1/10 second
		for (int i=0; i<chars.length; i++) {
			System.out.print(chars[i]);
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public static void waitSeconds(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch(InterruptedException e) {
			System.out.println(e);
		}
	}

	public static void shutdownComputer() {
		return;
		// try {
		// 	String shutdownCommand;
		// 	String operatingSystem = System.getProperty("os.name");

		// 	if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
		// 		shutdownCommand = "shutdown -h now";
		// 	} else if (operatingSystem.contains("Windows")) { // This will work on any version of windows including version 11 
		// 		shutdownCommand = "shutdown.exe -s -t 0";
		// 	} else {
		// 		throw new RuntimeException("Unsupported operating system.");
		// 	}

		// 	Runtime.getRuntime().exec(shutdownCommand);
		// 	System.exit(0);
		// } catch(RuntimeException | IOException e) {

		// 	System.out.println(e);
		// }
	}

}