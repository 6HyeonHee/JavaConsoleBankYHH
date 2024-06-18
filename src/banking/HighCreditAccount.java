package banking;

import banking.Account;

public class HighCreditAccount extends Account {

	int normalRate;
	String creditRank;
	
	public HighCreditAccount(String name, String accountNum, int money, int normalRate, String creditRank) {
		super(name, accountNum, money);
		this.normalRate = normalRate;
		this.creditRank = creditRank;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자>" + normalRate + "%");
		System.out.println("신용등급>" + creditRank);
	}
}
