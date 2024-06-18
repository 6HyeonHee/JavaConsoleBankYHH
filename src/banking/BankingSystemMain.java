package banking;

import java.util.Scanner;
import banking.AccountManager;

public class BankingSystemMain {

	public static void showMenu() {
		System.out.println("---- MENU ----");
		System.out.println("1. 계좌 개설");
		System.out.println("2. 입    금");
		System.out.println("3. 출    금");
		System.out.println("4. 계좌정보 출력");
		System.out.println("5. 프로그램 종료");
		System.out.print("선    택 : ");
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		AccountManager handler = new AccountManager(50);
		
		while(true) {
			// 1. 메뉴 출력
			showMenu();
			// 2. 사용자로부터 수행할 기능의 메뉴를 입력 받는다.
			int choice = scan.nextInt();
			
			// 3. switch문을 통해 선택한 번호에 따라 메서드를 호출한다.
			switch(choice) {
			case 1:
				handler.selectAccount();
				break;
			case 2:
				handler.depositMoney();
				break;
			case 3:
				handler.withdrawMoney();
				break;
			case 4:
				handler.showAccInfo();
				break;
			case 5:
				System.out.println("\n*** 프로그램 종료 ***");
				return;
			} // switch
		} // while
	} // main
} // class
