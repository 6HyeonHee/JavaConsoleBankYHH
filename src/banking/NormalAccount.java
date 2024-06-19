package banking;

import banking.Account;

public class NormalAccount extends Account {

	int normalRate;
	
	public NormalAccount(String name, String accountNum, int money, int normalRate) {
		super(name, accountNum, money);
		// 이자비율의정보를 초기화 할 수 있도록 만들기
		this.normalRate = normalRate;
	}
	
	// 이자율을 반영하여 입금하는 메서드 추가
    public void deposit(int amount) {
        // 이자율을 퍼센트로 계산하여 반영
        double interest = this.money * (this.normalRate / 100.0);
        this.money += interest + amount;
        // 소수점을 무조건 다 버린다.
        Math.floor(money);
    }
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		
		System.out.println("기본이자>" + normalRate + "%");
		System.out.println("---------------------");
	}

}
