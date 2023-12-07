package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("test name 1", "SEK", SEK.getName());
		assertEquals("test name 2", "DKK", DKK.getName());
		assertEquals("test name 3", "EUR", EUR.getName());

	}
	
	@Test
	public void testGetRate() {
		
		 assertEquals("test rate of currency SEK", Double.valueOf(0.15), SEK.getRate());
	     assertEquals("test rate of currency DKK", Double.valueOf(0.20), DKK.getRate());
	     assertEquals("test rate of currency EUR", Double.valueOf(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		 DKK.setRate(0.40);
	     assertEquals("Test: setting rate of DKK", Double.valueOf(0.40), DKK.getRate());
	     EUR.setRate(0.2);
		 assertEquals("Test: setting rate of EUR ",Double.valueOf(0.20),EUR.getRate());
	}
	
	@Test
	public void testGlobalValue() {
			
		assertEquals("test  convert SEK to universal 1", Integer.valueOf(15), SEK.universalValue(100));
		assertEquals("test convert EUR universal 2", Integer.valueOf(150), EUR.universalValue(100));
		
	}
	
	@Test
	public void testValueInThisCurrency() {
				
		assertEquals("test DKK",Integer.valueOf(20),DKK.universalValue(100));
		
	}

}
