import java.util.Scanner;

// 201812-1 小明上学
// 分类计数问题
public class DecOne {
	// 三种路灯使用的时间
	public static int red;
	public static int yellow;
	public static int green;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		Scanner input = new Scanner(System.in);
		red = input.nextInt();
		yellow = input.nextInt();
		green = input.nextInt();
		int n = input.nextInt();
		for (int i = 0; i < n; i++) {
			int k = input.nextInt();
			int t = input.nextInt();
			sum += getTime(k, t);
		}
		input.close();
		System.out.print(sum);
	}
	
	
	/*
	 * paras : kind为该计时的类型
	 * time : 一段路花费的时间或者灯的倒计时
	 */
	public static int getTime(int kind, int time) {
		switch (kind) {
		case 0:
			return time;
		case 1:	// 红灯
			return time;
		case 2: //黄灯
			return time + red;
		case 3: //绿灯
			return 0;
		default:
			return  -1;
		}
	}
}
