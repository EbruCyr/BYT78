package b_Money;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("Test  get name:", "SweBank",  SweBank.getName());
		assertEquals("Test  get name: ","Nordea",Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals("Test get currency: ", "SEK" ,  SweBank.getCurrency().getName());
		assertEquals("Test get currency: ","SEK",  Nordea.getCurrency().getName());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		Nordea.openAccount("laura");
		assertEquals("TODO", "laura" ,  Nordea.getAccountFromAccountlist("laura").getAccountName());


	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob",new Money(10000,SEK));
		SweBank.deposit("Bob",new Money(100000,SEK));
		assertEquals("Test: adding Deposit",Integer.valueOf(110000),SweBank.getBalance("Bob"));
	
	}
	
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {

		
		Nordea.deposit("Bob", new Money(1000, SEK));
		Nordea.withdraw("Bob", new Money(1000, SEK));
		assertEquals("TODO", Integer.valueOf(0) ,  Nordea.getBalance("Bob"));
		Nordea.withdraw("Bob", new Money(2000, SEK));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals("TODO", Integer.valueOf(0) ,  Nordea.getBalance("Bob"));
	}
	
}