import java.util.Scanner;

// 201803-1试题 : 跳一跳

public class MarchOne {
	// 记录上一次跳跃的状态,从1,2中取值
	private static int lastState = 1;
	// 记录上一次跳跃的得分
	private static int lastScore = 0;
	// 记录总得分
	private static int sum = 0;
	
	// 输入本次跳跃状态,改变sum,如果失败返回false
	private static Boolean jump(int state) {
		if (state == 0)
			return false;
		switch (lastState) {
		case 1:
			if (state == 1) {
				lastScore = 1;
			}
			else {
				lastScore = 2;
			}
			break;
		case 2:
			if (state == 1) {
				lastScore = 1;
			}
			else {
				lastScore += 2;
			}
			break;
		default:
			return false;
		}
		lastState = state;
		sum += lastScore;
		//System.out.println(lastScore);
		return true;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		input.close();
		String[] states = str.split(" ");
		for (String state : states) {
			//System.out.print(state);
			if (!jump(Integer.valueOf(state)))
				break;
		}
		System.out.println(sum);
	}
}
