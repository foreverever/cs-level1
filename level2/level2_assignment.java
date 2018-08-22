import java.util.*;

public class baseballGame {

	// 컴퓨터 난수 생성
	public List<Integer> getComNum() {
		List<Integer> comNum = new ArrayList<Integer>();
		Random rand = new Random();
		while (comNum.size() != 3) {
			int randNum = rand.nextInt(9) + 1;
			if (isOnlyNum(comNum, randNum)) {
				comNum.add(randNum);
			}
		}
		return comNum;
	}

	// 컴퓨터 난수 중복 체크
	public boolean isOnlyNum(List<Integer> comNum, int randNum) {
		for (int i = 0; i < comNum.size(); i++) {
			if (comNum.get(i) == randNum) {
				return false;
			}
		}
		return true;
	}

	// 사용자로부터 숫자 받기
	public List<Integer> getUserNum() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String[] inputArr = input.split("");
		List<Integer> userNum = new ArrayList();
		return inputList(inputArr, userNum);
	}

	// 사용자로부터 받은 입력값 리스트에 삽입
	public List<Integer> inputList(String[] inputArr, List<Integer> userNum) {
		for (int i = 0; i < inputArr.length; i++) {
			try {
				userNum.add(Integer.parseInt(inputArr[i]));
			} catch (NumberFormatException e) {
				userNum.clear();
				break;
			}
		}

		return userNum;
	}

	// 스트라이크 체크
	public int checkStrike(List<Integer> comNum, List<Integer> userNum) {
		int stk = 0;
		for (int i = 0; i < 3; i++) {
			if (comNum.get(i) == userNum.get(i)) {
				stk++;
			}
		}
		return stk;
	}

	// 볼 체크
	public int checkBall(List<Integer> comNum, List<Integer> userNum) {
		int ball = 0;
		for (int i = 0; i < 3; i++) {
			if (comNum.contains(userNum.get(i)) && comNum.get(i) != userNum.get(i)) {
				ball++;
			}
		}
		return ball;
	}

	// 결과 판독 및 출력
	public void printResult(int stk, int ball) {
		if (stk == 0 && ball == 0) {
			System.out.println("낫싱");
		} else if (stk != 0 && ball == 0) {
			System.out.println(stk + "스트라이크");
		} else if (stk == 0 && ball != 0) {
			System.out.println(ball + "볼");
		} else {
			System.out.println(stk + "스트라이크 " + ball + "볼");
		}
	}

	// 3스트라이크 판정
	public boolean isThreeStrike(int stk) {
		if (stk == 3) {
			return true;
		}
		return false;
	}

	// 사용자입력 예외 처리
	public boolean isNotRightUserNum(List<Integer> userNum) {
		if (checkRealNum(userNum))	return true;
		if (checkNumberOfNum(userNum))	return true;
		if (checkDuplNum(userNum))	return true;
		return false;
	}

	// 사용자 입력숫자 중복 체크
	public boolean checkDuplNum(List<Integer> userNum) {
		List<Integer> userSortNum = new ArrayList();
		for (int i = 0; i < userNum.size(); i++) {
			userSortNum.add(userNum.get(i));
		}
		Collections.sort(userSortNum);
		for (int i = 0; i < userSortNum.size() - 1; i++) {
			if (userSortNum.get(i) == userSortNum.get(i + 1)) {
				System.out.println("중복된 숫자가 존재합니다. 다시 입력하세요.");
				return true;
			}
		} 
		return false;
	}

	// 사용자 입력숫자 개수 체크
	public boolean checkNumberOfNum(List<Integer> userNum) {
		if (userNum.size() > 3) {
			System.out.println("숫자 개수가 초과하였습니다. 다시 입력하세요.");
			return true;
		}
		if (userNum.size() < 3 && userNum.size() >0) {
			System.out.println("숫자 개수가 부족합니다. 다시 입력하세요.");
			return true;
		}
		return false;
	}

	// 사용자 숫자외 입력 체크
	public boolean checkRealNum(List<Integer> userNum) {
		if (userNum.size() == 0) {
			System.out.println("숫자가 아닙니다. 다시 입력하세요.");
			return true;
		}
		return false;
	}

	// 게임시작
	public void startGame(List<Integer> comNum) {
		List<Integer> userNum = new ArrayList();
		while (true) {
			userNum = getUserNum();
			//System.out.println(comNum);
			if(isNotRightUserNum(userNum)) continue;
			int stk = checkStrike(comNum, userNum);
			int ball = checkBall(comNum, userNum);
			printResult(stk, ball);
			if (isThreeStrike(stk)) break;
		}
	}

	// 실행 함수
	public static void main(String[] args) {
		baseballGame bg = new baseballGame();
		List<Integer> comNum = new ArrayList();
		while (true) {
			comNum = bg.getComNum();
			System.out.println("숫자를 입력해주세요(1~9)");
			bg.startGame(comNum);
			System.out.println("3개의 숫자를 모두 맞히셨습니다!");
		}
	}
}
