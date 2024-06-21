package puzzle3by3;


public class SpecialAccount extends NormalAccount {

	
	public SpecialAccount(String name, String accountNum, int money, int normalRate) {
		super(name, accountNum, money, normalRate);
	}
	
	int despoitCount = 0;
	
	public void deposit(int amount) {
		despoitCount += 1;
        // 이자율을 퍼센트로 계산하여 반영
        double interest = this.money * (this.normalRate / 100.0);
        
        if(despoitCount%2==0) {
        	 this.money += interest + amount + 500;
        	 System.out.println("짝수번째 입금으로 추가금이 지급되었습니다.");
        } else {
        	this.money += interest + amount;        	
        }
        // 소수점을 무조건 다 버린다.
        Math.floor(money);
    }
}
