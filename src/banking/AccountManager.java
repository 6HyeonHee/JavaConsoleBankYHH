package banking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {
	
	private HashSet<Account> accountinfo = new HashSet<Account>();
	private int select;
	private Account newAccount;
	
	// 여기서의 i는 main의 개수(50개)를 표현하기 위함이다.
	public AccountManager(int i) {
		
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
		    
		Account tempAccount = null;
		    
		if (select == 1) {
		    System.out.print("기본이자%(정수형태로입력): ");
		    iNormalrate = scan.nextInt();
		    scan.nextLine();
		    tempAccount = new NormalAccount(iName, iAccountnum, iMoney, iNormalrate);
		} else if (select == 2) {
		    System.out.print("기본이자%(정수형태로입력): ");
		    iNormalrate = scan.nextInt();
		    scan.nextLine();
		    System.out.print("신용등급(A,B,C등급): ");
		    iCreditrank = scan.nextLine();
		        
		    tempAccount = new HighCreditAccount(iName, iAccountnum, iMoney, iNormalrate, iCreditrank); 
		}
		
		// 컬렉션에 인스턴스 추가
		boolean yn = accountinfo.add(tempAccount);
			if(yn==true) {
				System.out.println("입력되었습니다.");
			} else {
				System.out.println("중복된 인스턴스가 발견되었습니다.");
				System.out.println("중복계좌발견된.덮어쓸까요?(y or n)");
				String con = scan.nextLine();
				//equalsIgnoreCase : 대소문자를 구분 안함.
				if(con.equalsIgnoreCase("y")) {
					// 덮어쓰기 진행
					/*
					 새롭게 입력한 인스턴스(=account)로 기존의 저장된 인스턴스를 삭제한다.
					 우리의 입장에서는 서로 다른 인스턴스 이지만 Set의 입장에서는
					 동일한 인스턴스이므로 삭제가 가능한다.
					*/
					accountinfo.remove(tempAccount);
					// 새롭게 입력한 인스턴스를 추가한다.
					accountinfo.add(tempAccount);
					System.out.println("계좌 정보가 변경되었습니다..");
				} else {
					System.out.println("기존 정보를 유지합니다.");
				}
			}
		}


	// 입금
	public void depositMoney() {
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);

		Iterator<Account> itr = accountinfo.iterator();

		try {
			System.out.println("*** 입 금 ***");
			System.out.println("계좌번호와 입금할 금액을 입력하시오");
			System.out.print("계좌번호: ");
			String iAccountnum = scan.nextLine();
			System.out.print("입금액: ");
			int iDeposit = scan.nextInt();

			
			// 일반 for문을 통해 계좌번호 검색
			while (itr.hasNext()) {
				Account acc = itr.next();
				if(iAccountnum.compareTo(acc.accountNum) == 0) {
					// 보통계좌인 경우에만 이자율을 반영하여 입금
	                if (acc instanceof NormalAccount) {
	                    NormalAccount normalAcc = (NormalAccount) acc;
	                    normalAcc.deposit(iDeposit);
	                }
	                // 신용계좌인 경우 추가이자율도 반영
	                else if(acc instanceof HighCreditAccount) {
	                	HighCreditAccount highCreditAcc = (HighCreditAccount) acc;
	                    highCreditAcc.deposit(iDeposit);
	                } else {
	                	// NormalAccount가 아닌 경우 기본 입금 처리
	                    acc.money += iDeposit;
	                }
					isFind = true;
					break;
				}
			}
			
			if(isFind == false) {
				System.out.println("XXX 계좌번호가 없어 입금에 실패하였습니다 XXX");
			} else if(iDeposit<0) {
				throw new IllegalArgumentException("입금액에 음수를 입력할 수 없습니다.");
			} else if(iDeposit % 500 != 0) {
				throw new IllegalArgumentException("500원 단위의 금액을 입력해주세요");
			} else {				
				System.out.println("입금이 완료되었습니다.");
			}
			
			
		} catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
	}
	
	// 출금
	public void withdrawMoney() {
		boolean isFind = false;
		Iterator<Account> itr = accountinfo.iterator();
		Scanner scan = new Scanner(System.in);

		try {			
			System.out.println("*** 출 금 ***");
			System.out.println("계좌번호와 출금할 금액을 입력하시오");
			System.out.print("계좌번호: ");
			String iAccountnum = scan.nextLine();
			System.out.print("출금액: ");
			int iWithdraw = scan.nextInt();
			
			if(iWithdraw<0) {
				throw new IllegalArgumentException("입금액에 음수를 입력할 수 없습니다.");
			} else if(iWithdraw % 1000 != 0) {
				throw new IllegalArgumentException("1000원 단위의 금액을 입력해주세요");
			}
			
			// 일반 for문을 통해 계좌번호 검색
			while (itr.hasNext()) {
				Account acc = itr.next();
				// 계좌번호가 일치하면 작성한 금액만큼 뺀다.
				if(iAccountnum.compareTo(acc.accountNum) == 0) {
					
					// 잔액보다 더 많은 금액 출금시 전체출금 여부를 물어본다.
					if(iWithdraw > acc.money) {
						System.out.println("잔고부족. 전체금액을 출금할까요?(y or n)");
						scan.nextLine();
						String answer = scan.nextLine();
						
						if(answer.equals("y")) {
							acc.money = 0;
							System.out.println("전체금액을 출금하였습니다.");
						} else if (answer.equals("n")) {
							acc.money -= 0;
							System.out.println("출금한 금액이 없습니다.");
						}
						
					} else {
						acc.money -= iWithdraw;
						System.out.println("출금이 완료되었습니다.");
					}
					
					isFind = true;
					break;
				}
				
			}
			
			if(isFind == false) {
				System.out.println("XXX 계좌번호가 없어 출금에 실패하였습니다. XXX");
			}
			
		} catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요");
            scan.next(); // 잘못된 입력을 제거
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
	}
	
	// 계좌정보 삭제
	public void deleteAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.println("삭제할 계좌번호를 입력하세요:");
		String deleteAccount = scan.nextLine();
		
		boolean isDeleted = false;
		
		Iterator<Account> itr = accountinfo.iterator();
		
		while(itr.hasNext()) {
			Account acc = itr.next();
			if(deleteAccount.compareTo(acc.accountNum)==0) {
				itr.remove();
				isDeleted = true;
				break;
			}
		}
		if (isDeleted == false) {
			System.out.println("삭제된 데이터가 없습니다.");
		}
		else {
			System.out.println("데이터가 삭제되었습니다.");
		}
	}
	
	
	// 전체계좌정보출력
	public void showAccInfo() {
		Iterator<Account> itr = accountinfo.iterator();
		
		while (itr.hasNext()) {
			Account acc = itr.next();
			System.out.println("----- 전체 계좌 정보 출력 -----");
			acc.showAccInfo();
		}
	}
	
	// 정보 저장
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/banking/account_info.obj"));
			
			for(Account acc : accountinfo) {
				out.writeObject(acc);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// 정보 호출
	public void readAccountInfo() {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("src/banking/account_info.obj"));
			
			while(true) {
				Account account = (Account)in.readObject();
				accountinfo.add(account);
			}
		} catch(Exception e) {
			System.out.println("역직렬화 중 예외발생");
		} System.out.println("계좌 정보가 복원되었습니다.");
	}
	
	// 자동저장
	boolean autoSave = false;
	AutoSaver t = null;
	
	public void autoSave() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1.자동저장 On / 2.자동저장 Off");
		int save = scan.nextInt();
		scan.nextLine();
		
		
		t = new AutoSaver();
		if(save == 1) {
			if(!autoSave) {
				// 자동저장 멘트 실행
				System.out.println("5초마다 자동저장을 실행합니다.");
				t.setDaemon(true);
				t.start();
				autoSave = true;
			} else {
				System.out.println("이미 자동저장이 실행중입니다.");
			}
			
			
		} else if(save == 2) {
			if (autoSave && t != null) {
				// 저장저장 중지
				t.interrupt();
				autoSave = false;
				System.out.println("자동저장을 중지합니다.");				
			} 
		} else {
			System.out.println("1 또는 2를 입력해주세요");
		}
	}
}
