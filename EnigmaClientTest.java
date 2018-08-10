import java.util.Scanner;

public class EnigmaClientTest {

	public static void main(String[] args) {
		Enigma useEnigma = new Enigma();
		Scanner userInput = new Scanner(System.in);
		int userChoice = instruction(userInput);
		if(userChoice == 1) {
			defaultSetting(useEnigma, userInput); // run the default setting 
		}
		else {
			customSetting(useEnigma, userInput); // ask users to create their own rings
		}
		
		
		
		
	}

	// custom setting methods
	@SuppressWarnings("resource")
	/**
	 * Custom setting.
	 * @param theEnigma Enigma class
	 * @param theUserInput scanner input
	 */
	public static void customSetting(Enigma theEnigma, Scanner theUserInput) {
		// Getting user's inner rotor and middle rotor, and message to encrypt or decrypt
		System.out.print("Create your own inner rotor: ");
		String userInnerRotor = theUserInput.next();
		System.out.print("Create your own middle rotor: ");
		String userMidRotor = theUserInput.next();
		theEnigma =  new Enigma(userInnerRotor, userMidRotor);
		System.out.println("What message do you want to encrypt?: ");
		theUserInput = new Scanner(System.in); // restart scanner to read next line
		String message  = theUserInput.nextLine();
		System.out.println("Your encrypted message is: "+theEnigma.encrypt(message));
		System.out.println();
		theEnigma.reset();
		
	}
	
	// default setting methods
	/**
	 * Default setting.
	 * @param theEnigma Enigma class
	 * @param theUserInput scanner input
	 */
	public static void defaultSetting(Enigma theEnigma, Scanner theUserInput) {
		 
		String encryptedCode = theEnigma.encrypt(theEnigma.getMessage());// for default use only
		theEnigma.reset();
		System.out.println("Using default Settings");
		System.out.println();
		System.out.println("The Enigma model will use the following settings:");
		System.out.println("\tOuter ring: "+ theEnigma.getOuterrotor());
		System.out.println("\tMiddle ring: "+ theEnigma.getMiddleRotor());
		System.out.println("\tInner ring: "+ theEnigma.getInnerRotor());
		System.out.println();
		System.out.println("Would you like to:");
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		System.out.println("3. Run Default Example");
		int option = theUserInput.nextInt();
		if (option == 1) {
			System.out.print("Type your message: ");
			theUserInput = new Scanner(System.in);
			final String message = theUserInput.nextLine();
			System.out.println("Your encrypted message is: " + theEnigma.encrypt(message));
			theEnigma.reset();
		}
		else if(option == 2) {
			System.out.print("Type your message: ");
			theUserInput = new Scanner(System.in);
			final String message = theUserInput.nextLine();
			System.out.println("Your encrypted message is: " + theEnigma.decrypt(message));
			theEnigma.reset();
		}
		else {
			System.out.println("You have chosen to use default setting: "+ theEnigma.getMessage());
			System.out.println("The encrypted code is: "+ encryptedCode);
			System.out.println("After encrypting, the rotors look like: ");
			System.out.println("Outer Rotor: "+ theEnigma.getOuterrotor());
			System.out.println("Middle Rotor: "+ theEnigma.getMiddleRotor());
			System.out.println("Inner Rotor: "+ theEnigma.getInnerRotor());
			theEnigma.reset();
			System.out.println();
			System.out.println("The default encrypted code is:  "+ encryptedCode);
			System.out.println("The decrypted code is: "+theEnigma.decrypt(encryptedCode));
			System.out.println("After decrypting, the rotors look like: ");
			System.out.println("Outer Rotor: "+ theEnigma.getOuterrotor());
			System.out.println("Middle Rotor: "+ theEnigma.getMiddleRotor());
			System.out.println("Inner Rotor: "+ theEnigma.getInnerRotor());
			System.out.println();
			theEnigma.reset();
		}
		
	}
	
	/**
	 * Instruction for users and users' choice on running the program
	 * @param userInput
	 * @return
	 */
	public static int instruction(Scanner userInput) {
		
		System.out.println("Welcome to Enigma!");
		System.out.println("Please select from the menu below: ");
		System.out.println("1. Use default rotor settings");
		System.out.println("2. Input custom rotor settings");
		System.out.println("Selection:");
		int choice = userInput.nextInt();
		
		return choice;
	}

}
