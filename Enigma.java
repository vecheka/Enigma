

public class Enigma {

	/** Default Outer Rotor use to encryt and decrypt the message.*/
	public static final String outerRotor = "#BDFHJLNPRTVXZACEGIKMOQSUWY"; 
	/** Default Inner Rotor use to encryt and decrypt the message.*/
	private  String innerRotor ="#GNUAHOVBIPWCJQXDKRYELSZFMT";
	/** Default Middle Rotor use to encryt and decrypt the message.*/
	private  String middleRotor = "#EJOTYCHMRWAFKPUZDINSXBGLQV";
	/** Default message for testing.*/
	public static final String message = "Computer programming is lots of fun";
	/** Count of rotations on each rotor.*/
	public static int count = 0; // keep track of rotation to turn middle rotor
	
	/**
	 * Default Constructor.
	 */
	public Enigma(){
		//default constructor - constructs enigma machine as shown in spec
		
	}
	
	/**
	 * Constructor that takes custom inner rotor, and middle rotor.
	 * @param theInnerRotor inner rotor
	 * @param theMiddleRotor middle rotor
	 */
	public Enigma(final String theInnerRotor, final String theMiddleRotor){
		//non-default constructor - constructs machine with user specified inner and middle rotors
		//non-default constructor should call method(s) to make sure rotors meet requirements
		this.innerRotor = theInnerRotor;
		this.middleRotor = theMiddleRotor;
		
	}
	
	/**
	 * Verify if the given rotor is valid.
	 * @param theRotor rotor to be verified
	 * @return true if it is valid
	 */
	private boolean isRotorValid (final String theRotor){
		//verify that rotStr is exactly 27 chars long 
		//verify that all chars from english alphbet occur only once 
		//verify that rotor starts with a # char
		if(theRotor.length() == outerRotor.length() && theRotor.charAt(0) == outerRotor.charAt(0)) {
			return true;
		}
		// count occurences
		int countOccur = 0;
		for (char ch = 'A'; ch < 'Z'; ch++) {
			for (int i = 0; i < theRotor.length(); i++) {
				if(ch == theRotor.charAt(i)) {
					countOccur++;
				}
			}
			if(countOccur >= 2) {
				return false;
			}
			countOccur = 0;
		}
		
		return true;
		
	} 
	
	/**
	 * Encrypt the given message.
	 * @param theMessage message to be encrypted
	 * @return an encrypted message
	 */
	public String encrypt (String theMessage){
		//call to encodeChar  (Inner-Outer-Middle-Outer)
		//call to rotateClockwise
		String encodedMessage = "";
		theMessage =  theMessage.replace(" ", "#").toUpperCase();
		if(isRotorValid(innerRotor) && isRotorValid(middleRotor) 
				&& isRotorValid(outerRotor)) {
			int i = 0;
			while(i < theMessage.length()) {
				char ch =  theMessage.charAt(i);
				encodedMessage += encodeChar(ch);
				rotateClockwise();
				count++;
				i++;
			}
		}
		
		
		
		return encodedMessage;
		
	}
	
	
	
	/**
	 * Decrypt the given message.
	 * @param theMessage message to be encrypted
	 * @return an decrypted message
	 */
	public String decrypt (String theMessage){ 
		//call to rotateAntiClockwise
		//call to decodeChar (Outer-Middle-Outer-Inner)
		String decodedMessage = "";
		theMessage = theMessage.toUpperCase();
		if(isRotorValid(innerRotor) && isRotorValid(middleRotor) 
				&& isRotorValid(outerRotor)) {
			int i = 0;
			while(i<theMessage.length()) {
				char ch = theMessage.charAt(i);
				decodedMessage += decodeChar(ch);
				rotateClockwise();
				count++;
				i++;
					
			}
		}
		return decodedMessage ;
	} 
	
	/**
	 * Helper method to encode a given character.
	 * @param theChar character to be encoded
	 * @return an encoded character
	 */
	private char encodeChar (final char theChar){
		//this finds the code character for ch (as per spec)
		char code = '\0'; // null char 
		int inner = innerRotor.indexOf(theChar);
		char outer = outerRotor.charAt(inner);
		int middle = middleRotor.indexOf(outer);
		code = outerRotor.charAt(middle);

		return code;
	} 
	
	/**
	 * Helper method to decode a given character.
	 * @param theChar character to be encoded
	 * @return an decoded character
	 */
	private char decodeChar (final char theChar){
		//this finds the original character for the code ch (as per spec)
		char message = '\0';
		int outer = outerRotor.indexOf(theChar);
		char middle = middleRotor.charAt(outer);
		int inner = outerRotor.indexOf(middle);
		message = innerRotor.charAt(inner);
		if(message == '#') {
			message = ' ';
		}
		
		return message;
	} 
	
	/**
	 * Helper method to rotate the inner rotor clock wise.
	 */
	private void rotateClockwise(){ 
		String lastCha = ""+innerRotor.charAt(innerRotor.length()-1);
		for(int i=0; i<innerRotor.length()-1; i++) {
			lastCha += innerRotor.charAt(i);
		}
		innerRotor = lastCha;
		
		// Once inner rotor return to origin, rotate middle rotor by one step
		if(count==innerRotor.length()) {
			count = 0;
			String lCha = ""+middleRotor.charAt(middleRotor.length()-1);
			for(int i=0; i<middleRotor.length()-1; i++) {
				lCha += middleRotor.charAt(i);
			}
			middleRotor = lCha;
		}
		
	} 
	
//	private void rotateAntiClockwise(){ 
//		
//	} 
	
	/**
	 * Reset the innerRotor and middleRotor back to the default.
	 */
	public void reset (){
		//resets to align all # chars on all rotors (returns rotors to initial configuration)
		innerRotor ="#GNUAHOVBIPWCJQXDKRYELSZFMT";
	    middleRotor = "#EJOTYCHMRWAFKPUZDINSXBGLQV";	
	    count = 0;
	  
	}
	
	
	/**
	 * Get innerRotor.
	 * @return innerRotor
	 */
	public  String getInnerRotor() {
		return innerRotor;
	}
	
	/**
	 * Set the innerRotor to a given innerRotor.
	 * @param theInnerRotor innerRotor to be used
	 */
	public void setInnerRotor(final String theInnerRotor) {
		this.innerRotor = theInnerRotor;
	}
	
	/**
	 * Get the middleRotor
	 * @return middleRotor
	 */
	public String getMiddleRotor() {
		return middleRotor;
	}
	
	/**
	 * Set the middleRotor to a given middleRotor
	 * @param theMiddleRotor middleRotor to be used
	 */
	public void setMiddleRotor(final String theMiddleRotor) {
		this.middleRotor = theMiddleRotor;
	}
	
	/**
	 * Get the outerRotor
	 * @return outerRotor
	 */
	public String getOuterrotor() {
		return outerRotor;
	}
	
	
	/**
	 * Get the message being encrypted.
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	}



