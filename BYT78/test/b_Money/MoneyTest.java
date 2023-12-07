package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		
	     assertEquals("Test for getting regular amount for EUR", Integer.valueOf(1000), EUR10.getAmount());
	     assertEquals("Test for getting zero amount for EUR", Integer.valueOf(0), EUR0.getAmount());	
	     assertEquals("Test for getting 20000 amount for SEK", Integer.valueOf(20000), SEK200.getAmount());
	     assertEquals("Test for getting negative amount for SEK", Integer.valueOf(-10000), SEKn100.getAmount());
	
	}
	
	@Test
	public void testGetCurrency() {
	
	assertEquals("Test for getting currency", EUR, EUR10.getCurrency());
	assertEquals("Test for getting currency", EUR, EUR0.getCurrency());
	assertEquals("Test for getting currency", SEK, SEK200.getCurrency());
	assertEquals("Test for getting currency", SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		
		assertEquals(" String to value 200 SEK", "200 SEK", SEK200.toString());
		assertEquals(" String to value negative SEK", "-100 SEK", SEKn100.toString());
		assertEquals(" String to value 0 EUR", "0 EUR", EUR0.toString());
        assertEquals(" String to value 10 EUR", "10 EUR", EUR10.toString());
		
	}

	@Test
	public void testGlobalValue() {
		 assertEquals(" global value of 1500 SEK", Integer.valueOf(1500), SEK100.universalValue());
	     assertEquals("global value of 3000 EUR", Integer.valueOf(3000), EUR20.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		 assertEquals("Is value of 1000 EUR and 10000 SEK are equal ?", true, EUR10.equals(SEK100));
	     assertEquals("Is value of 2000 EUR and 10000 SEK are not equal ?", false, EUR20.equals(SEK100));
	  
	}

	@Test
	public void testAdd() {
	
	   assertEquals("Adding two SEK amounts", Integer.valueOf(30000), SEK100.add(SEK200).getAmount());
	   assertEquals("Currency should be SEK", SEK, SEK100.add(SEK200).getCurrency());
		
	}

	@Test
	public void testSub() {
		assertEquals(" currencies below zero", "-100 SEK", SEK100.sub(SEK200).toString());
        assertEquals(" currencies above zero", "0 SEK", SEK100.sub(SEK100).toString());
   
	}

	@Test
	public void testIsZero() {
		  assertTrue(SEK0.isZero());   // Test for zero amount
	      assertFalse(SEK100.isZero()); // Test for non-zero amount
	      assertFalse(SEKn100.isZero()); // Test for negative non-zero amount
	}

	@Test
	public void testNegate() {
		
		
		 Money negatedSEK100 = SEK100.negate();
		 Money negatedEUR20 = EUR20.negate();


		 assertEquals("Negate 100 SEK should be -100 SEK", new Money(-10000, SEK).toString(), negatedSEK100.toString());
		 assertEquals("Negate 20 EUR should be -20 EUR", new Money(-2000, EUR).toString(), negatedEUR20.toString());

	}

	@Test
	public void testCompareTo() {
		 assertEquals( 0, SEK100.compareTo(SEK100));
	     assertEquals(-1, SEK100.compareTo(EUR20));
	     assertEquals( +1, SEK200.compareTo(SEK0));
	 
	}
}

