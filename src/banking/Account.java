package banking;

public class Account {
	String name;
	String accountNum;
	int money;
	
	public Account(String name, String accountNum, int money) {
		this.name = name;
		this.accountNum = accountNum;
		this.money = money;
	}
	
	public void showAccInfo() {

		System.out.println("계좌번호>" + accountNum);
		System.out.println("고객이름>" + name);
		System.out.println("잔고>" + money);

	}
	
}
