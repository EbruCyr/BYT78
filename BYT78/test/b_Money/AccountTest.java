package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 10, 10, new Money(100, SEK), SweBank, "Alice");
     // testAccount.addTimedPayment("1", 10, 10, new Money(100, SEK), SweBank, "Alice");
      //error here timed payment already exist.
      assertTrue( testAccount.timedPaymentExists("1"));
      testAccount.removeTimedPayment("1");
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
 
		testAccount.addTimedPayment("1",3,0,new Money(10000,SEK),SweBank,"Alice");
		assertTrue(testAccount.timedPaymentExists("1"));
		assertEquals("Before tick1:",Integer.valueOf(10000000),testAccount.getBalance());
		testAccount.tick();
		assertEquals("after tick1:",Integer.valueOf(9990000),testAccount.getBalance());
		testAccount.tick();
		assertEquals("After tick2:",Integer.valueOf(9990000),testAccount.getBalance());
		testAccount.tick();
		assertEquals("After tick3:",Integer.valueOf(9990000),testAccount.getBalance());
		testAccount.tick();
		assertEquals("After tick4:",Integer.valueOf(9990000),testAccount.getBalance());
		testAccount.tick();
		assertEquals("After tick5:",Integer.valueOf(9980000),testAccount.getBalance());
		testAccount.tick();
	}

	@Test
	public void testAddWithdraw() {
		 testAccount.withdraw(new Money(5000000, SEK));
       assertEquals("Test: withdraw Hans", Integer.valueOf(5000000), testAccount.getBalance());
       testAccount.withdraw(new Money(990000000, SEK));
       //error , doesn't have enough money.
       assertEquals("Test: withdraw Hans", Integer.valueOf(5000000), testAccount.getBalance());
	}
	
	@Test
	public void testGetBalance() {
		 assertEquals("Test: Get account balance", Integer.valueOf(10000000), testAccount.getBalance());
	}
}

