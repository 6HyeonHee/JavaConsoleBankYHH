package puzzle3by3;

public class HighCreditAccount extends Account {

	int normalRate;
	String creditRank;
	
	public HighCreditAccount(String name, String accountNum, int money, int normalRate, String creditRank) {
		super(name, accountNum, money);
		this.normalRate = normalRate;
		this.creditRank = creditRank;
	}


	private double getCreditRankRate() {
		switch(creditRank) {
		case "A": case "a":
			return 0.07; // 7%
		case "B": case "b":
			return 0.04; // 4%
		case "C": case "c":
			return 0.02; // 2%
		default:
			return 0.0;
		}	
	}
	
	// 이자율을 반영하여 입금하는 메서드 추가
    public void deposit(int amount) {
        double basicRate = this.money * (this.normalRate / 100.0);
        double extraRate = this.money * getCreditRankRate();
        this.money += basicRate + extraRate + amount;
        // 소수점을 무조건 다 버린다.
        Math.floor(money);
    }
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자 > " + normalRate + "%");
		System.out.println("신용등급 > " + creditRank);
		System.out.println("---------------------");
	}
	
	@Override
	public String toString() {
		String str = "계좌번호:" + accountNum + ", "
				+ "이름:" + name + ", "
				+ "잔고:" + money + ", "
				+ "기본이자:" + normalRate + ", "
				+ "신용등급:" + creditRank;
		return str;
	}
	
}
