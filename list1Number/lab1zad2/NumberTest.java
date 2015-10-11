package lab1zad2;

import junit.framework.TestCase;

public class NumberTest extends TestCase {

	public void setUp() throws Exception {

	}

	public void testNumber() {
		
		assertNotNull( Number.number );
		assertTrue( Number.number < 2049 );
		Number a = new Number(12);
		Number b = a;
		assertSame( a, b );
	}
	
	public void getBase() {

	}
	
	public void testConvertNumber() {
		assertEquals( "1228", Number.convertNumber( 2048, 12 ) );
		assertFalse( Integer.toBinaryString(123).equals( Number.convertNumber(124, 2) ) );
		
		//Edited by oskam:
		assertNotSame(Number.convertNumber(42,2), Number.convertNumber(42,2));
	}

	public void testPrintResult() {
		
	}
}
