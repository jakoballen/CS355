package tests;

import code.TicketMachine;
import static org.junit.Assert.*;
import org.junit.Test;

/*
 * assert methods
 *  assertEquals (message, expected, actual)	
 *  assertEquals (expected, actual, delta)
 *  assertEquals (expected, actual)
 *  // many more versions of assertEquals(...)
 *  assertTrue (condition)
 *  assertFalse (condition)
 *  assertNotNull (object-variable)
 *  assertNull (object-variable)
 *  assertSame (object1, object2)
 *  assertNotSame (object1, object2)
 *  fail (message)
 */

public class TestTicketMachine {
	
	@Test
	public void testTicketMachine() {
		TicketMachine tm = new TicketMachine(100);
		assertNotNull(tm);
	}
	
	@Test //story 1
	public void testGetCost() {
		TicketMachine tm = new TicketMachine(100);
		int cost = tm.getCost();
		assertEquals(100, cost);
	}

	@Test //story 2
	public void testInsert(){
		TicketMachine tm = new TicketMachine(100);
		tm.insert(0);
		assertEquals(0, tm.getAmountInserted());
		tm.insert(25);
		assertEquals(25, tm.getAmountInserted());
		tm.insert(20);
		tm.insert(15);
		assertEquals(60, tm.getAmountInserted());

	}

	@Test //story 3
	public void testAmountRemaining(){
		TicketMachine tm = new TicketMachine(100);
		tm.insert(25);
		int remainder = tm.getRemainingBalance();
		assertEquals(75, remainder);
	}

	@Test //story 4
	public void testPrint(){
		TicketMachine tm = new TicketMachine(100);
		tm.insert(200);
		assertEquals("First Class Ticket", tm.print());
		tm.insert(100);
		assertEquals("Normal Ticket", tm.print());

	}

	@Test //story 5
	public void testTotal(){
		TicketMachine tm = new TicketMachine(50);
		tm.insert(50);
		tm.insert(50);
		tm.insert(25);
		int total = tm.getTotal();
		assertEquals(125, total);
	}

	@Test //story 6
	public void testRefund(){
		TicketMachine tm = new TicketMachine(50);
		tm.insert( 25);
		tm.refund(25);
		assertEquals(0, tm.getAmountInserted());
		tm.insert(50);
		tm.print();
		tm.insert( 50);
		tm.print();
		tm.refund(50);
		assertEquals(1, tm.getNumberSold());
		tm.insert(100000);
		tm.print();
		assertEquals(2, tm.getNumberSold());
	}

	@Test //story 7
	public void testInsufficientFunds(){
		TicketMachine tm = new TicketMachine(50);
		tm.insert(25);
		assertEquals("Insufficient funds, insert: $25 to print ticket.", tm.print());
	}

	@Test //story 8
	public void testNegative(){
		TicketMachine tm = new TicketMachine(50);
		String error = tm.insert(-10000);
		assertEquals("Negative amounts are not allowed!", error);
		error = tm.insert(-2147483648); //min int value
		assertEquals("Negative amounts are not allowed!", error);
		assertNull(tm.insert(2147483647)); //max int value
	}

	@Test //story 9
	public void testReport(){
		TicketMachine tm = new TicketMachine(100);
		String report = tm.getReport();
		assertEquals("0 first class, with $0 collected, and 0 normal tickets, with $0 collected, sold with $0 collected.", report);
		tm.insert(100);
		tm.print();
		tm.insert(100);
		tm.print();
		report = tm.getReport();
		assertEquals("0 first class, with $0 collected, and 2 normal tickets, with $200 collected, sold with $200 collected.", report);
		tm.insert(25);
		tm.insert(62);
		report = tm.getReport();
		assertEquals("0 first class, with $0 collected, and 2 normal tickets, with $200 collected, sold with $287 collected.", report);
		tm.insert(200);
		tm.print();
		report = tm.getReport();
		assertEquals("1 first class, with $200 collected, and 2 normal tickets, with $200 collected, sold with $400 collected.", report);

	}

	@Test //story 10
	public void testFirstClass(){
		TicketMachine tm = new TicketMachine(50);
		tm.insert(100);
		assertEquals("First Class Ticket", tm.print());

	}
	
}	// end - class TestTicketMachine
