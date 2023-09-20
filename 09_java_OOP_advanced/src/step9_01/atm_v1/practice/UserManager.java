package step9_01.atm_v1.practice;

import java.util.Scanner;

public class UserManager {
	
	Scanner scan = new Scanner(System.in);
	User[] user = null;  // 여러 명의 user 정보를 저장할 배열
	int userCount = 0;  // user 인원
	
	void printAllUser() {
		for (int i = 0; i < user.length; i++) {
			user[i].printAccount();
		}
	}
	
	void addUser() {
		if (userCount == 0) {
			user = new User[1];
			
			System.out.print("[가입] id를 입력하세요 : ");
			String id = scan.next();
			
			user[userCount] = new User();
			user[userCount].id = id;
			System.out.println("[메시지] ID : " + id + " 가입 되었습니다.\n");
			userCount++;
		}
		else {
			System.out.print("[가입] id를 입력하세요 : ");
			String id = scan.next();
			
			// 입력받은 아이디가 이미 배열에 존재하는지 검사하는 부분
			boolean isDuple = false;
			for (int i = 0; i < userCount; i++) {
				if (user[i].id.equals(id)) {  // user[i]만 쓰지 않도록 주의 (user[i].id로 써야 함)
					isDuple = true;
				}
			}
			
			if (!isDuple) {  // 중복이 아니면 isDuple이 false이고, !false로 if 안의 문장이 실행된다.
				User[] temp = user;  // User형의 temp 배열에 user 배열을 복사한다.
				user = new User[userCount + 1];  // 기존의 user 배열보다 한 칸 더 크게 만들기 (추가할 거니까)
				
				// temp에 있는 데이터를 user에 복사한다. (i의 범위에 주의)
				for (int i = 0; i < userCount; i++) {
					user[i] = temp[i];
				}
				temp = null;  // temp를 비워주어야 한다.
				
				user[userCount] = new User();
				user[userCount].id = id;
				System.out.println("[메시지] ID : " + id + " 가입 되었습니다.\n");
				userCount++;
				
			}
			else {  // 중복(이미 존재하는 아이디)이면
				System.out.println("[메시지] " + id + "는 이미 가입된 아이디입니다.");
			}
		}
	}
	
	User getUser(int idx) {  
		return user[idx];  // user 배열에 저장된 User 객체 중 하나를 리턴 (ex. 원하는 1명의 유저는 identifier를 인자로 넘겨줌.)
	}
	
	int logUser() {
		int identifier = -1;
		System.out.print("[입력] 아이디를 입력하세요 : ");
		String name = scan.next();
		for (int i = 0; i < userCount; i++) {
			if (name.equals(user[i].id)) {
				identifier = i;
				System.out.println("[ " + user[identifier].id + " ] 님 로그인.\n");
			}
		}
		
		return identifier;
	}
	
	void leave() {
		System.out.print("탈퇴할 아이디를 입력하세요 : ");
		String name = scan.next();
		int identifier = -1;
		for (int i = 0; i < userCount; i++) {
			if (name.equals(user[i].id)) {
				identifier = i;
			}
		}
		
		if (identifier == -1) {
			System.out.println("[메시지] 아이디를 다시 확인하세요.");
			return;
		}
		
		System.out.println("ID : " + user[identifier].id + "가 탈퇴되었습니다.");
		
		User[] temp = user;
		user = new User[userCount - 1];
		
		for (int i = 0; i < identifier; i++) {
			user[i] = temp[i];
		}
		
		for (int i = identifier; i < userCount-1; i++) {
			user[i] = temp[i+1];
		}
		
		userCount--;
		
	}
	
}
