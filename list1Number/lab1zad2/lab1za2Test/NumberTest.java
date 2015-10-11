package lab1zad2.lab1za2Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lab1zad2.Number;

import org.junit.*;

public class NumberTest {
	
	private Number nNumber;
	private String str;
	private int number;
	
	@Before
	public void setUp() throws Exception {
		nNumber = new Number(2);
		str = Number.convertNumber(8, 2);
		number = Number.number;
	}
	
	@Test
	public void test() {
		assertEquals( str, "1000" );
	}
	
	@Test(expected = Exception.class)
	public void test2() {
		new Number(2134);
	}
	
	@Ignore
	@Test(timeout=100)
	public void test3() {
		assertNotNull( number );
		assertTrue( number < 2049 );
		Number a = nNumber;
		Number b = a;
		assertSame( a, b );
		
		for( int i=0; i >= 0; i++){
			int x = 0;
			x = x + i;
		}
		
		assertTrue( Integer.toBinaryString(123).equals( Number.convertNumber(124, 2) ) );
	}
	
	@After
	public void tearDown() throws Exception {
		nNumber = null;
		str = null;
		number = 0;
	}
	
}
