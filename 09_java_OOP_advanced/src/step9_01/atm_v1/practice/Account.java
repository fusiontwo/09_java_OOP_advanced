package step9_01.atm_v1.practice;

public class Account {
	 
	String number;
	int money;
	
	void printOwnAccount() {
		System.out.println("계좌 번호 : " + this.number + " 잔고 : " + this.money);
	}
}
