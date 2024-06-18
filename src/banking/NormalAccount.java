package banking;

import banking.Account;

public class NormalAccount extends Account {

	public NormalAccount(String name, String accountNum, int money) {
		super(name, accountNum, money);
		// 이자비율의정보를 초기화 할 수 있도록 만들기
	}

}
