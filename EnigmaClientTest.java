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
	public static void customSetting(Enigma useEnigma, Scanner userInput) {
		// Getting user's inner rotor and middle rotor, and message to encrypt or decrypt
		System.out.print("Create your own inner rotor: ");
		String userInnerRotor = userInput.next();
		System.out.print("Create your own middle rotor: ");
		String userMidRotor = userInput.next();
		useEnigma =  new Enigma(userInnerRotor, userMidRotor);
		System.out.println("What message do you want to encrypt?: ");
		userInput = new Scanner(System.in); // restart scanner to read next line
		String message  = userInput.nextLine();
		System.out.println("Your encrypted message is: "+useEnigma.encrypt(message));
		System.out.println();
		useEnigma.reset();
		
	}
	
	// default setting methods
	public static void defaultSetting(Enigma useEnigma, Scanner userInput) {
		 
		String encryptedCode = useEnigma.encrypt(useEnigma.getMessage());// for default use only
		useEnigma.reset();
		System.out.println("Using default Settings");
		System.out.println();
		System.out.println("The Enigma model will use the following settings:");
		System.out.println("\tOuter ring: "+ useEnigma.getOuterrotor());
		System.out.println("\tMiddle ring: "+ useEnigma.getMiddleRotor());
		System.out.println("\tInner ring: "+ useEnigma.getInnerRotor());
		System.out.println();
		System.out.println("Would you like to:");
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		System.out.println("3. Run Default Example");
		int option = userInput.nextInt();
		if (option == 1) {
			System.out.println("You have chosen to encrypt the default string: "+ useEnigma.getMessage());
			System.out.println("The encrypted code is: "+ encryptedCode);
			System.out.println();
			useEnigma.reset();
		}
		else if(option==2) {
			System.out.println("You have chosen to decrypt the default string: "+ encryptedCode);
			System.out.println("The decrypted code is: "+useEnigma.decrypt(encryptedCode));
			System.out.println();
			useEnigma.reset();
		}
		else {
			System.out.println("You have chosen to use default setting: "+ useEnigma.getMessage());
			System.out.println("The encrypted code is: "+ encryptedCode);
			System.out.println("After encrypting, the rotors look like: ");
			System.out.println("Outer Rotor: "+ useEnigma.getOuterrotor());
			System.out.println("Middle Rotor: "+ useEnigma.getMiddleRotor());
			System.out.println("Inner Rotor: "+ useEnigma.getInnerRotor());
			useEnigma.reset();
			System.out.println();
			System.out.println("The default encrypted code is:  "+ encryptedCode);
			System.out.println("The decrypted code is: "+useEnigma.decrypt(encryptedCode));
			System.out.println("After decrypting, the rotors look like: ");
			System.out.println("Outer Rotor: "+ useEnigma.getOuterrotor());
			System.out.println("Middle Rotor: "+ useEnigma.getMiddleRotor());
			System.out.println("Inner Rotor: "+ useEnigma.getInnerRotor());
			System.out.println();
			useEnigma.reset();
		}
		
	}
	
	// instruction for users and users' choice on running the program
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
