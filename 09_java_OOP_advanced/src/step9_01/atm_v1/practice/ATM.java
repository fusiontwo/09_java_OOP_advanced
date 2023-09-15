package step9_01.atm_v1.practice;

import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	UserManager userManager = new UserManager();
	int identifier = -1;
	
	void printMainMenu() {
		
		int sel = 0;
		while (true) {
			System.out.println("[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입], [4.회원탈퇴], [0.종료] : ");
			sel = scan.nextInt();
			
			if (sel == 1) login();
			else if (sel == 2) logout();
			else if (sel == 3) join();
			else if (sel == 4) leave();
			else if (sel == 0) break;
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
	void login() {
		identifier = userManager.logUser();
		
		if (identifier != -1) {
			printAccountMenu();
		}
		else {
			System.out.println("[메시지] 로그인 실패");
		}
	}
	
	void logout() {
		System.out.println("테스트 logout");
	}
	
	void join() {
		userManager.addUser();
	}
	
	void leave() {
		System.out.println("테스트 leave");
	}
	
	void printAccountMenu() {
		int sel = 0;
		while (true) {
			System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
			sel = scan.nextInt();
			
			if (sel == 1) {
				System.out.println("생성 테스트");
			}
			else if (sel == 2) {
				System.out.println("삭제 테스트");
			}
			else if (sel == 3) {
				System.out.println("조회 테스트");
			}
			else if (sel == 0) {
				System.out.println("로그아웃 테스트");
				break;
			}
		}
	}

}
