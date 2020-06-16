/*
 * TicketMachine - class to simulate a simple transportation ticket machine
 */
package code;

public class TicketMachine {
	// data
	private int cost;				// cost of one regular ticket
	private int amountInserted;	    // the amount of money inserted into the machine
	private int totalInserted;
	private int numberSold;			// the number of tickets sold
	private int firstClassSold;
	
	// constructors
	// -- default constructor
	public TicketMachine () {
		this(0);
		amountInserted = 0;
		totalInserted = 0;
	}

	// -- one-arg ticket cost constructor
	public TicketMachine (int cost) {
		this.cost = cost;
		totalInserted = 0;
		amountInserted = 0;
	}
	
	// other methods besides constructors
	// -- getter for cost
	public int getCost () {
		return cost;
	}

	public String insert(int amount){
		if(amount < 0){
			return "Negative amounts are not allowed!";
		}else {
			amountInserted += amount;
			totalInserted += amount;
			return null;
		}
	}

	public void refund(int amount){
		totalInserted -= amount;
		amountInserted -= amount;
		if(amount == cost){
			numberSold--;
		}
	}

	public int getTotal(){
		//return (cost*numberSold)+amountInserted;
		return totalInserted;
	}

	public int getAmountInserted(){
		return amountInserted;
	}

	public int getRemainingBalance(){
		return cost - amountInserted;
	}

	public int getNumberSold(){
		return numberSold + firstClassSold;
	}

	public String print(){
		if(amountInserted < cost){
			return "Insufficient funds, insert: $"+ getRemainingBalance() + " to print ticket.";
		}else{
			if (amountInserted >= cost) {
				if(amountInserted >= (2 * cost)){
					firstClassSold++;
					refund(amountInserted - (2*cost));
					amountInserted = 0;
					return "First Class Ticket";
				}else {
					numberSold++;
					refund(amountInserted - cost);
					amountInserted = 0;
					return "Normal Ticket";
				}

			}

		}
		return null;

	}

	public String getReport(){
		return firstClassSold +" first class, with $"+ firstClassSold*(cost*2)+" collected, and "+ numberSold + " normal tickets, with $"+numberSold*cost+ " collected, sold with $"+totalInserted +" collected.";
	}
	
}	// end - class TicketMachine
