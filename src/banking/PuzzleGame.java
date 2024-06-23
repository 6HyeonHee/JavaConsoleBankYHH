package banking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PuzzleGame {

	String[][] arr = new String[3][3];
	// 0은 인덱스에 있는 것이라서 0으로 초기화하면 안됨.
	int xRow = -1;
	int xCol = -1;
	
	// 움직이는 횟수
	int moveCnt = 0;
	final int MAX_MOVES = 100;
	
	// 난수 생성해서 3x3 배열로 출력
	public void randNum() {
		
		ArrayList<String> numbers = new ArrayList<>();
		
		// 1부터 8까지의 숫자를 생성하여 리스트에 추가
		for(int num=1; num<9; num++) {
			numbers.add(String.valueOf(num));
		}
		// 공백을 표시하는 X도 리스트에 추가
		numbers.add("X");
		
		// 리스트를 랜덤하게 섞기
		Collections.shuffle(numbers);
		
		int index=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				
				arr[i][j] = numbers.get(index);
				
				// X의 위치 가져오기
				if(arr[i][j].equals("X")) {
					xRow = i;
					xCol = j;
				}
				
				index++;
				
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	// 퍼즐 이동 기능
	public void move(String direction) {
		
		
		int newRow = xRow;
		int newCol = xCol;
		
		// 방향키 지정
		switch(direction) {
		case "w":
			newRow++;
			moveCnt++;
			break;
		case "a":
			newCol++;
			moveCnt++;
			break;
		case "s":
			newRow--;
			moveCnt++;
			break;
		case "d":
			newCol--;
			moveCnt++;
			break;
		default:
			System.out.println("잘못된 방향키 입니다.");
		}
		
		// 경계값 검사
		if(newRow >=0 && newRow<3 && newCol>=0 && newCol<3) {
			// x의 위치 변경
			arr[xRow][xCol] = arr[newRow][newCol];
			arr[newRow][newCol] = "X";
			xRow = newRow;
			xCol = newCol;
		} else {
			System.out.println("움직일 공간이 없습니다.");
		}
		
		// 퍼즐이 정렬되었는지 확인하기
		if(isSorted()) {
			System.out.println("정답입니다 :)");
			askRestart();
		}
	}
	
	public boolean isSorted() {
		int expectedValue = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) {
                    return arr[i][j].equals("X");
                } else if (!arr[i][j].equals(String.valueOf(expectedValue))) {
                    return false;
                }
                expectedValue++;
            }
        }
        return true;
	}
	
	// 공백 이동 후 퍼즐 출력
	public void printPuzzle() {
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
	}
	
	// 재시작
	public void askRestart() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("재시작 할까요? (y or n)");
			String answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("y")) {
				startGame();
				break;
			} else if (answer.equalsIgnoreCase("n")) {
				System.out.println("게임을 종료합니다.");
				System.exit(0);
			} else {
				System.out.println("잘못된 값입니다. y또는n을 입력하세요");
			}
		}
	}
	
	// 게임 시작 메소드
	public void startGame() {
		randNum();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("움직일 수 있는 횟수[" + moveCnt + "/" + MAX_MOVES + "]");
			System.out.println("[이동] a:Left, d:Right, w:Up, s:Down");
			System.out.println("[종료] x:Exit");
			String input = scan.nextLine();
			if(input.equals("x")) {
				break;
			} 
			move(input);
			printPuzzle();
			
			if (moveCnt >= MAX_MOVES) {
                System.out.println("횟수 모두 소진하였습니다.");
                askRestart();
            }
		}
		scan.close();	
		
	}
}
