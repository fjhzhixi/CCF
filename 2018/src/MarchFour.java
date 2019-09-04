import java.util.Scanner;

// 201803-4 ��������
/*
 * �Կ������Ĳ�������
 * ����˼·
 * 1. A�������õ÷�score��
 * 2. B�������õ÷�scoreС
 * 3. ÿ�α���ÿһ�����
 */

public class MarchFour {
	public static int[][] board = new int[3][3];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					board[j][k] = input.nextInt();
				}
			}
			int score;
			if ((score = getScore(1)) != 0) {
				System.out.println(score);
			}
			else if ((score = getScore(2)) != 0) {
				System.out.println(score);
			}
			else {
				System.out.println(dfs(1));
			}
		}

	}
	
	// �жϵ�r���Ƿ���type��������ʤ��(h:0-2,type:1/2
	public static boolean rowOK(int r, int type) {
		return board[r][0] == type && 
				(board[r][0] == board[r][1] && board[r][1] == board[r][2]);
	}
	// �жϵ�c���Ƿ�ʤ��
	public static boolean columnOK(int c, int type) {
		return board[0][c] == type &&
				(board[0][c] == board[1][c] && board[1][c] == board[2][c]);
	}
	// �ж϶Խ����Ƿ�ʤ��
	public static boolean diagonalOK(int type) {
		return (board[0][0] == type && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
			   (board[0][2] == type && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
	}
	// ����ո���Ŀ
	public static int blankCount() {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) {
					sum++;
				}
			}
		}
		return sum;
	}
	// ����÷���Ŀ,���û��ʤ������0,ʤ���򷵻ط���
	public static int getScore(int type) {
		boolean win = false;
		int score = 1;
		// �ж��Ƿ�ʤ��
		if (rowOK(0, type) || rowOK(1, type) || rowOK(2, type)) 
			win = true;
		if (columnOK(0, type) || columnOK(1, type) || columnOK(2, type))
			win = true;
		if (diagonalOK(type))
			win = true;
		if (!win)
			return 0;
		else {
			score += blankCount();
			return (type == 1) ? score : -score;
		}
	}
	
	//�����㷨
	// A�µ�����1��ʾ,B�µ�����2��ʾ,0��ʾ�ո�
	public static int dfs(int type) {
		if(blankCount() == 0) {
			// ��������ƽ�ַ���0
			return 0;
		}
		int Max = -10; 	//��С�ķ���-1
		int Min = 10;	//���ķ���+1
		//����ѡ�����ž���
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int score;
				if(board[i][j] == 0) {
					//�ո�
					board[i][j] = type;
					score = getScore(type);
					//�����ʤ
					if (score != 0) {
						board[i][j] = 0;
						return score > 0 ? Math.max(Max, score) : Math.min(Min, score);
					}
					// û�л�ʤ����һ���˼�����
					if (type == 1) {
						Max = Math.max(Max, dfs(2));
					}
					else {
						Min = Math.min(Min, dfs(1));
					}
					board[i][j] = 0;
				}
			}
		}
		return (type == 1) ? Max : Min;
	}
}
