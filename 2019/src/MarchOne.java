import java.util.Scanner;

// 201909-1 小中大
// 使用归并排序
public class MarchOne {
	public final static int MAX = 100000;
	public static int[] sorted = new int[MAX];	//输入的有序序列
	public static int n;	// 数组数目
	public static boolean ascOrder = true;	//为true则为升序(默认)
	public static double mid; //中位数
	public static int max; //最大值
	public static int min; //最小值
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		for(int i = 0; i < n; i++) {
			sorted[i] = input.nextInt();
		}
		input.close();
		checkOrder();
		analyse();
		out();
	}
	
	public static void out() {
		//按最大值,中位数,最小值顺序输出
		System.out.print(max + " ");
		if ((mid - (int)mid) == 0) {	//中位数为整数
			System.out.print((int)mid + " ");
		}
		else {
			System.out.printf("%.1f", mid);
			System.out.print(" ");
			
		}
		System.out.print(min);
	}
	
	public static void checkOrder() {
		// 检测输入是升序还是降序
		for (int i = 0; i < n-1; i++) {
			if (sorted[i] > sorted[i+1]) {
				ascOrder = false;
				break;
			}
		}
	}
	
	public static void analyse() {
		// 统计数据
		max = ascOrder ? sorted[n-1] : sorted[0];
		min = ascOrder ? sorted[0] : sorted[n-1];
		mid = (n%2 == 0) ? (sorted[n/2 - 1] + sorted[n/2]) / 2.0 : (float)(sorted[(n-1)/2]); 
	}

}
