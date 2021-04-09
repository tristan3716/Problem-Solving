import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
class Pos {
	int row;
	int col;
	
	Pos(int row, int col) {
		this.row = row; this.col = col;
	}
	
	public String toString() {
		return String.format("%d %d", row, col);
	}
}
public class Main {
	static int[][] board;
	static ArrayList<Pos> empty = new ArrayList<Pos>(); // 빈 칸의 좌표 리스트
	
	static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static void vcheck(Pos pos, boolean[] avail) { // 불가능한 숫자를 인덱스로 하는 값을 false로 바꾸어줌
		Boolean[] nums = new Boolean[10];
		Arrays.fill(nums, true);
		int curCol = pos.col;
		
		for (int r = 0; r < 9; r++) {
			int alreadyInput = board[r][curCol];
			nums[alreadyInput] = false;
		}
		
		for (int i = 1; i <= 9; i++)
			avail[i] = avail[i] && nums[i];
	}
	
	static void hcheck(Pos pos, boolean[] avail) {
		Boolean[] nums = new Boolean[10];
		Arrays.fill(nums, true);
		int curRow = pos.row;
		
		for (int c = 0; c < 9; c++) {
			int alreadyInput = board[curRow][c];
			nums[alreadyInput] = false;
		}
		
		for (int i = 1; i <= 9; i++)
			avail[i] = avail[i] && nums[i];
	}
	
	static void scheck(Pos pos, boolean[] avail) {
		Boolean[] nums = new Boolean[10];
		Arrays.fill(nums, true);
		
		// 현재 pos가 속한 사각형의 첫 번째 원소의 행과 열
		int bsFirstRow = pos.row/3*3;
		int bsFirstCol = pos.col/3*3;
		
		for (int r = bsFirstRow; r < bsFirstRow+3; r++) {
			for (int c = bsFirstCol; c < bsFirstCol+3; c++) {
				int alreadyInput = board[r][c];
				nums[alreadyInput] = false;
			}
		}
		
		for (int i = 1; i <= 9; i++)
			avail[i] = avail[i] && nums[i];
	}
	
	static boolean availEmpty(boolean[] avail) {
		for (int i = 1; i <= 9; i++)
			if (avail[i]) return false;
		return true;
	}
	
	static void solve(int emptyIdx) { // empty[emptyIdx]에 가능한 숫자 채우기
		/* 종료 조건 1 시작: 마지막에 도달*/
		int emptyNum = empty.size();
		if (emptyIdx == emptyNum) {
		
		printBoard();System.exit(0);} // 빈 칸을 모두 순회하면 종료
		/* 종료 조건 1 끝 */
		
		// 이번엔 어떤 칸에 새 숫자를 넣을 것인지
		Pos pos = empty.get(emptyIdx);
		
		// 이 칸에 대해 가능한 숫자의 인덱스의 값을 boolean[] avail에 true로 저장
		boolean[] avail = new boolean[10];
		Arrays.fill(avail, true);
		vcheck(pos, avail);
		hcheck(pos, avail);
		scheck(pos, avail);
		
		/* 종료 조건 2 시작: 가능한 숫자가 없음 */
		if (availEmpty(avail)) return;
		/* 종료 조건 2 */
		
		// 숫자 넣어보고 다음 위치 호출
		for (int i = 1; i <= 9; i++) {
			if (avail[i]) {
//System.out.printf("%s\n", Arrays.toString(avail));
//System.out.printf("%d, %d - %d\n", pos.row, pos.col, i);
				board[pos.row][pos.col] = i;
				solve(emptyIdx+1);
				board[pos.row][pos.col] = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		board = new int[9][9];
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int cur = sc.nextInt();
				board[i][j] = cur;
				
				if (cur==0) empty.add(new Pos(i, j));
				// 빈 칸의 좌표를 empty에 추가
			}
		}
		sc.close();
		
		solve(0);
	}
}