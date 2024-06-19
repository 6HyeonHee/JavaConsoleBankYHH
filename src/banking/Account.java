package banking;


import java.util.Objects;
import java.util.Scanner;

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
		System.out.println("---------------------");
		System.out.println("계좌번호>" + accountNum);
		System.out.println("고객이름>" + name);
		System.out.println("잔고>" + money);
	}
	
	
	
	@Override
	public int hashCode() {
		int returnCode = Objects.hash(this.accountNum);
		return returnCode;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Account account = (Account) obj;
		return Objects.equals(accountNum, account.accountNum);
	}
	
}



//public void askAdd() {
//	Scanner scan = new Scanner(System.in);
//	
//	System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
//	String answer = scan.nextLine();
//	if(answer == "y") {
//		System.out.println("계좌정보를 변경합니다.");
//	} else if(answer == "n") {
//		System.out.println("계좌정보를 유지합니다.");
//	}
//}
