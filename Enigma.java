import javax.xml.stream.events.EndDocument;

public class Enigma {

	public static final String outerRotor = "#BDFHJLNPRTVXZACEGIKMOQSUWY"; 
	private static String innerRotor ="#GNUAHOVBIPWCJQXDKRYELSZFMT";
	private static String middleRotor = "#EJOTYCHMRWAFKPUZDINSXBGLQV";
	public static final String message = "Computer programming is lots of fun";
	public static int count = 0; // keep track of rotation to turn middle rotor
	
	public Enigma(){
		//default constructor - constructs enigma machine as shown in spec
		
	}
	
	public Enigma(String s1, String s2){
		//non-default constructor - constructs machine with user specified inner and middle rotors
		//non-default constructor should call method(s) to make sure rotors meet requirements
		this.innerRotor = s1;
		this.middleRotor = s2;
		
	}
	
	private boolean isRotorValid (String rotStr){
		//verify that rotStr is exactly 27 chars long 
		//verify that all chars from english alphbet occur only once 
		//verify that rotor starts with a # char
		if(rotStr.length()==27 && rotStr.charAt(0)== '#') {
			return true;
		}
		// count occurences
		int countOccur = 0;
		for (char ch='A'; ch<'Z'; ch++) {
			for (int i=0; i<rotStr.length();i++) {
				if(ch==rotStr.charAt(i)) {
					countOccur++;
				}
			}
			if(countOccur>=2) {
				return false;
			}
			countOccur=0;
		}
		
		return true;
		
	} 
	
	public String encrypt (String message){
		//call to encodeChar  (Inner-Outer-Middle-Outer)
		//call to rotateClockwise
		String encodedMessage = "";
		message =  message.replace(" ", "#").toUpperCase();
		if(isRotorValid(innerRotor) && isRotorValid(middleRotor) 
				&& isRotorValid(outerRotor)) {
			int i =0;
			while(i<message.length()) {
				char ch =  message.charAt(i);
				encodedMessage += encodeChar(ch);
				rotateClockwise();
				count++;
				i++;
			}
		}
		
		
		
		return encodedMessage;
		
	}
	
	
	

	public String decrypt (String message){ 
		//call to rotateAntiClockwise
		//call to decodeChar (Outer-Middle-Outer-Inner)
		String decodedMessage = "";
		message = message.toUpperCase();
		if(isRotorValid(innerRotor) && isRotorValid(middleRotor) 
				&& isRotorValid(outerRotor)) {
			int i = 0;
			while(i<message.length()) {
				char ch = message.charAt(i);
				decodedMessage += decodeChar(ch);
				rotateClockwise();
				count++;
				i++;
					
			}
		}
		return decodedMessage ;
	} 
	
	private char encodeChar (char ch){
		//this finds the code character for ch (as per spec)
		char code = '\0'; // null char 
		int inner = innerRotor.indexOf(ch);
		char outer = outerRotor.charAt(inner);
		int middle = middleRotor.indexOf(outer);
		code = outerRotor.charAt(middle);

		return code;
	} 
	
	private char decodeChar (char ch){
		//this finds the original character for the code ch (as per spec)
		char message = '\0';
		int outer = outerRotor.indexOf(ch);
		char middle = middleRotor.charAt(outer);
		int inner = outerRotor.indexOf(middle);
		message = innerRotor.charAt(inner);
		if(message=='#') {
			message = ' ';
		}
		
		return message;
	} 
	
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
	
	public void reset (){
		//resets to align all # chars on all rotors (returns rotors to initial configuration)
		innerRotor ="#GNUAHOVBIPWCJQXDKRYELSZFMT";
	    middleRotor = "#EJOTYCHMRWAFKPUZDINSXBGLQV";	
	    count = 0;
	  
	}
	
	
	
	public static String getInnerRotor() {
		return innerRotor;
	}
	public static void setInnerRotor(String innerRotor) {
		Enigma.innerRotor = innerRotor;
	}
	public static String getMiddleRotor() {
		return middleRotor;
	}
	public static void setMiddleRotor(String middleRotor) {
		Enigma.middleRotor = middleRotor;
	}
	public static String getOuterrotor() {
		return outerRotor;
	}
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Enigma.count = count;
	}

	public static String getMessage() {
		return message;
}
	}



