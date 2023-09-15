package step9_01.atm_v1.practice;

public class User {
	
	String id;  // 유저의 아이디
	int accCount;  // 어떤 유저가 가지고 있는 계좌의 개수
	Account[] acc;  // 어떤 유저가 가지고 있는 계좌 배열
	
	void printAccount() {
		
		// 어떤 유저가 가지고 있는 모든 계좌를 출력
		for (int i = 0; i < accCount; i++) {
			acc[i].printOwnAccount();
		}
	}
	
}
