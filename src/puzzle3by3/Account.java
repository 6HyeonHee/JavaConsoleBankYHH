package puzzle3by3;


import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;


public class Account implements Serializable {
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
		System.out.println("계좌번호 > " + accountNum);
		System.out.println("고객이름 > " + name);
		System.out.println("잔고 > " + money);
	}
	
	
	
	@Override
	public int hashCode() {
		// 계좌번호의 해시값을 반환
		return accountNum.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Account acc = (Account)obj;
		// 계좌번호가 동일한 값인지 확인하여 boolean값 반환
		if(acc.accountNum.equals(this.accountNum)) {
			return true;
		} else {
			return false;
		}		
	}

}




