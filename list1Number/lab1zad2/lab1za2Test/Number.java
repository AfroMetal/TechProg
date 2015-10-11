package lab1zad2.lab1za2Test;

public class Number {
	
	private static String[] characters = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	static int number = 2048;
	
	public Number( int base ) {
		printResult( convertNumber(number, base), number, base);
	}

	public static Integer getBase( String[] args ) throws Exception {
		Integer base = Integer.parseInt( args[0] );
		if( base >= 2 && base <= 16 ) {
			return base;
		}
		else {
			throw new Exception("base have to be between 2 and 16");
		}
	}
	
	public static String convertNumber( int number, int base ) {
		String outputNumber = "";
		int current = number;
		while( current > 0 ) {
			outputNumber += characters[current % base];
			current = current / base;
		}
		outputNumber = new StringBuilder(outputNumber).reverse().toString();
		return outputNumber;
	}
	
	public static void printResult( String result, int number, int base ) {
		System.out.println( number 
							+ " (base 10) = " 
							+ result 
							+ " (base " 
							+ base + ")" );
	}
	
	public static void main( String[] args) {
		
		try {
			new Number( getBase(args) );
		} catch( NumberFormatException e) {
			System.err.println( "NumberFormatException: " + e.getMessage() );
		} catch( ArrayIndexOutOfBoundsException e) {
			System.err.println( "ArrayIndexOutOfBoundsException: " + e.getMessage() );
		} catch( Exception e) {
			System.err.println( "Exception: " + e.getMessage() );
		}
	}

}
