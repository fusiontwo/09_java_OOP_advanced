package step9_01.atm_v1.practice;

import java.util.Random;
import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	UserManager userManager = new UserManager();
	Random ran = new Random();
	int identifier = -1;
	
	void printMainMenu() {
		
		while (true) {
			
			System.out.println("[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입], [4.회원탈퇴], [0.종료] : ");
			int sel = scan.nextInt();
			
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
		if (identifier == -1) {
			System.out.println("[메시지] 로그인을 하신 후 이용하실 수 있습니다.");
		}
		else {
			identifier = -1;
			System.out.println("[메시지] 로그아웃 되었습니다.");
		}
	}
	
	void join() {
		userManager.addUser();
	}
	
	void leave() {
		userManager.leave();
	}
	
	void printAccountMenu() {

		while (true) {
			System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
			int sel = scan.nextInt();
			
			String makeAccount = Integer.toString(ran.nextInt(90001) + 10000);
			
			if (sel == 1) {
				if (userManager.user[identifier].accCount == 0) {  // user 객체의 identifier가 n일 때, n번째 user의 accCount(계좌 개수가 0인 경우)
					userManager.user[identifier].acc = new Account[1];  // user 객체의 identifier가 n일 때, n번째 user의 계좌 배열 acc에 Account 한 칸 생성
					
					userManager.user[identifier].acc[0] = new Account();
					userManager.user[identifier].acc[0].number = makeAccount;  // 0번째 계좌의 필드 number에 계좌번호 할당
				}
				else {
					Account[] temp = userManager.getUser(identifier).acc;  // Account형 배열 temp에 acc 배열을 복사해놓는다.
					int tempAccCount = userManager.getUser(identifier).accCount;  // .getUser()로 받아온 User 객체에 있는 accCount를 임시 저장
					userManager.user[identifier].acc = new Account[tempAccCount + 1];  // accCount보다 1칸 더 크게 acc 배열 생성
					for (int i = 0; i < tempAccCount; i++) {  // temp 배열 복사
						userManager.user[identifier].acc[i] = temp[i];
					}
					userManager.user[identifier].acc[tempAccCount] = new Account();  // 추가한 계좌 1칸에 Account 객체를 생성
					userManager.user[identifier].acc[tempAccCount].number = makeAccount;  // 추가한 계좌 1칸에 생성한 Account 객체에 있는 필드 number에 계좌번호 할당
					
				}
				userManager.user[identifier].accCount++;  // user 객체의 identifier가 n일 때, n번째 user의 accCount(계좌 개수)를 1 증가시킴
				System.out.println("[메시지] " + makeAccount + " 계좌가 생성되었습니다.");
				
			}
			else if (sel == 2) {
				if (userManager.user[identifier].accCount == 0) {
					System.out.println("[메시지] 더 이상 삭제할 수 없습니다.");
					continue;
				}
				else if (userManager.user[identifier].accCount == 1) {
					System.out.println("[메시지] 계좌번호 " + userManager.user[identifier].acc[0].number + " 삭제되었습니다.\n");
					userManager.user[identifier].acc = null;
				}
				else {
					System.out.print("삭제하고 싶은 계좌 번호를 입력하세요.");
					String deleteAccount = scan.next();  // 위에서 계좌 번호를 Integer.toString으로 문자열로 바꿨었기 때문에 .next()로 받음
					int tempAccAccount = userManager.user[identifier].accCount;  // 특정 user의 계좌 개수를 저장
					int delIdx = -1;  // 삭제하려는 계좌의 인덱스를 저장할 변수 (디폴트는 -1로 설정)
					for (int i = 0; i < tempAccAccount; i++) {
						if (deleteAccount.equals(userManager.user[identifier].acc[i].number)) {  // 삭제하고 싶은 계좌와 계좌 배열인 acc의 인덱스 i에 있는 Account 객체의 필드에 있는 number와 비교
							delIdx = i;
						}
					}
					
					if (delIdx == -1) {
						System.out.println("삭제하고 싶은 계좌 번호를 다시 확인하세요.\n");
						continue;
					}
					else {
						System.out.println("[메시지] 계좌 번호 : " + userManager.user[identifier].acc[delIdx].number + " 삭제 되었습니다.\n");
						
						Account[] temp = userManager.user[identifier].acc;
						userManager.user[identifier].acc = new Account[tempAccAccount - 1];  // 한 칸 줄인 acc 배열을 생성
						
						for (int i = 0; i < delIdx; i++) {
							userManager.user[identifier].acc[i] = temp[i];
						}
						
						for (int i = delIdx; i < tempAccAccount - 1; i++) {  // 위에서 한 칸 줄였음 범위 주의
							userManager.user[identifier].acc[i] = temp[i+1];  // temp의 인덱스 i+1임.
						}
					}
				}
				userManager.user[identifier].accCount--;  // 계좌 개수 1 감소시키기
				
			}
			else if (sel == 3) {
				if (userManager.user[identifier].accCount == 0) {
					System.out.println("생성된 계좌가 없습니다.\n");
				}
				else {
					userManager.user[identifier].printAccount();
				}
			}
			else if (sel == 0) {
				logout();
				break;
			}
		}
	}

}
