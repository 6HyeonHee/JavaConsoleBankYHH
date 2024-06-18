package banking;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
	
	private ArrayList<Account> lists;
	private int select;
	
	public AccountManager(int i) {
		lists = new ArrayList<Account>();
	}

	// 계좌선택
	public void selectAccount() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*** 신규계좌개설 ***");
		System.out.println("---- 계좌선택 ----");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용계좌");
		
		select = scan.nextInt();
		
		makeAccount();
	}

	// 계좌개설
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iName, iAccountnum, iCreditrank; 
		int iMoney, iNormalrate;
		
		System.out.print("계좌번호: ");
		iAccountnum = scan.nextLine();
		System.out.print("고객이름: ");
		iName = scan.nextLine();
		System.out.print("잔고: ");
		iMoney = scan.nextInt();
		
		if (select == 1) {
			System.out.print("기본이자%(정수형태로입력): ");
			iNormalrate = scan.nextInt();
			NormalAccount normal = new NormalAccount(iName, iAccountnum, iMoney, iNormalrate);
			lists.add(normal);
		} else if (select == 2) {
			System.out.print("기본이자%(정수형태로입력): ");
			iNormalrate = scan.nextInt();
			System.out.print("신용등급(A,B,C등급): ");
			// nextLine은 안되는데 next는 된다.. 뭔 이상현상인가..?
			iCreditrank = scan.next();
			
			HighCreditAccount high = new HighCreditAccount(iName, iAccountnum, iMoney, iNormalrate, iCreditrank); 
			lists.add(high);
		}
		
		System.out.println("계좌개설이 완료되었습니다.");
	}
	
	// 입금
	public void depositMoney() {
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);

		System.out.println("*** 입 금 ***");
		System.out.println("계좌번호와 입금할 금액을 입력하시오");
		System.out.print("계좌번호: ");
		String iAccountnum = scan.nextLine();
		System.out.print("입금액: ");
		int iDeposit = scan.nextInt();
		
		// 일반 for문을 통해 계좌번호 검색
		for(int i=0; i<lists.size(); i++) {
			Account acc = lists.get(i);
			if(iAccountnum.compareTo(acc.accountNum) == 0) {
				// 일치하면 작성한 금액만큼 더한다.
				acc.money += iDeposit;
				System.out.println("입금이 완료되었습니다.");
				isFind = true;
				break;
			}
		}
		
		if(isFind == false) {
			System.out.println("XXX 계좌번호가 없어 입금에 실패하였습니다 XXX");
		}
	}
	
	// 출금
	public void withdrawMoney() {
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);

		System.out.println("*** 출 금 ***");
		System.out.println("계좌번호와 출금할 금액을 입력하시오");
		System.out.print("계좌번호: ");
		String iAccountnum = scan.nextLine();
		System.out.print("출금액: ");
		int iWithdraw = scan.nextInt();
		
		// 일반 for문을 통해 계좌번호 검색
		for(int i=0; i<lists.size(); i++) {
			Account acc = lists.get(i);
			if(iAccountnum.compareTo(acc.accountNum) == 0) {
				// 일치하면 작성한 금액만큼 뺀다.
				acc.money -= iWithdraw;
				System.out.println("출금이 완료되었습니다.");
				isFind = true;
				break;
			}
		}
				
		if(isFind == false) {
			System.out.println("XXX 계좌번호가 없어 출금에 실패하였습니다. XXX");
		}
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		for(int i = 0; i < lists.size(); i++) {
			lists.get(i).showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
