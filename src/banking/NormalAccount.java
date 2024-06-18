package banking;

import banking.Account;

public class NormalAccount extends Account {

	int normalRate;
	
	public NormalAccount(String name, String accountNum, int money, int normalRate) {
		super(name, accountNum, money);
		// 이자비율의정보를 초기화 할 수 있도록 만들기
		this.normalRate = normalRate;
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자>" + normalRate + "%");
	}

}
