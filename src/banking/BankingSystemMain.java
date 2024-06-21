package banking;


import java.util.InputMismatchException;
import java.util.Scanner;
import banking.AccountManager;
import banking.AutoSaver;

public class BankingSystemMain {

	public static void showMenu() {
		System.out.println("---- MENU ----");
		System.out.println("1. 계좌 개설");
		System.out.println("2. 입    금");
		System.out.println("3. 출    금");
		System.out.println("4. 계좌정보 삭제");
		System.out.println("5. 계좌정보 출력");
		System.out.println("6. 저장 옵션");
		System.out.println("7. 프로그램 종료");
		System.out.print("선    택 : ");
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		AccountManager handler = new AccountManager(50);

		AutoSaver as = null;
		
		handler.readAccountInfo();
		
		try {
			while(true) {
				// 1. 메뉴 출력
				showMenu();
				// 2. 사용자로부터 수행할 기능의 메뉴를 입력 받는다.
				int choice = scan.nextInt();
				scan.nextLine();
				
				if(choice<1 || choice>7) {
					throw new IllegalArgumentException("1~7 사이의 정수를 입력하세요");
				}
				
				// 3. switch문을 통해 선택한 번호에 따라 메서드를 호출한다.
				switch(choice) {
				case ICustomDefine.MAKE:
					handler.selectAccount();
					break;
				case ICustomDefine.DEPOSIT:
					handler.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					handler.withdrawMoney();
					break;
				case ICustomDefine.DELETE:
					handler.deleteAccount();
					break;
				case ICustomDefine.INQUIRE:
					System.out.println("----- 전체 계좌 정보 출력 -----");
					handler.showAccInfo();
					break;
				case ICustomDefine.SAVE:
					
					try {
						if(!as.isAlive()) {
							as = new AutoSaver(handler);
						}
					} catch(Exception e) {
						as = new AutoSaver(handler);
					}
					
					handler.dataSaveOption(as);
					break;
				case ICustomDefine.EXIT:
					System.out.println("\n*** 프로그램 종료 ***");
					handler.saveAccountInfo();
					return;
				} // switch
			} // while
		} catch(InputMismatchException e) {
			System.out.println("숫자를 입력하시오");
		} catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

	} // main
} // class
