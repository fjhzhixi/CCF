import java.util.Scanner;

// 201803-4 棋盘评估
/*
 * 对抗搜索的博弈问题
 * 解题思路
 * 1. A尽可能让得分score大
 * 2. B尽可能让得分score小
 * 3. 每次遍历每一种情况
 */

public class MarchFour {
	public static int[][] board = new int[3][3];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//输入
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
	
	// 判断第r行是否是type类型棋子胜利(h:0-2,type:1/2
	public static boolean rowOK(int r, int type) {
		return board[r][0] == type && 
				(board[r][0] == board[r][1] && board[r][1] == board[r][2]);
	}
	// 判断第c列是否胜利
	public static boolean columnOK(int c, int type) {
		return board[0][c] == type &&
				(board[0][c] == board[1][c] && board[1][c] == board[2][c]);
	}
	// 判断对角线是否胜利
	public static boolean diagonalOK(int type) {
		return (board[0][0] == type && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
			   (board[0][2] == type && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
	}
	// 计算空格数目
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
	// 计算得分数目,如果没有胜利返回0,胜利则返回分数
	public static int getScore(int type) {
		boolean win = false;
		int score = 1;
		// 判断是否胜利
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
	
	//搜索算法
	// A下的棋用1表示,B下的棋用2表示,0表示空格
	public static int dfs(int type) {
		if(blankCount() == 0) {
			// 棋盘已满平局返回0
			return 0;
		}
		int Max = -10; 	//最小的分数-1
		int Min = 10;	//最大的分数+1
		//遍历选择最优决策
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int score;
				if(board[i][j] == 0) {
					//空格
					board[i][j] = type;
					score = getScore(type);
					//如果获胜
					if (score != 0) {
						board[i][j] = 0;
						return score > 0 ? Math.max(Max, score) : Math.min(Min, score);
					}
					// 没有获胜则另一个人继续下
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
