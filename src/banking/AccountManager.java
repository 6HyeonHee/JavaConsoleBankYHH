package banking;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
	
	private ArrayList<Account> lists;
	
	public AccountManager() {
		lists = new ArrayList<Account>();
	}
	
	// 계좌개설
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iName, iAccountnum, iMoney;
		System.out.println("*** 신규계좌개설 ***");
		System.out.print("계좌번호: ");
		iAccountnum = scan.nextLine();
		System.out.print("고객이름: ");
		iName = scan.nextLine();
		System.out.print("잔고: ");
		iMoney = scan.nextLine();
		System.out.println("계좌개설이 완료되었습니다.");
	}
	
	// 입금
	public void depositMoney() {
		
	}
	
	// 출금
	public void withdrawMoney() {
		
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		
	}
}
