import java.util.Scanner;

// 201809-1����
// �򵥵ļ�����
public class SepOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[] prices = new int[n];
		for (int i = 0; i < n; i++) {
			prices[i] = input.nextInt();
		}
		input.close();
		getPrice(prices);
	}

	// �����ݵ�ƽ��ֵ
	public static int mean(int ...values) {
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		return (sum / values.length);
	}
	// ���ݵ�һ��Ķ�������ڶ���Ķ���
	public static void getPrice(int ...prices) {
		int n = prices.length;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				System.out.print(mean(prices[i], prices[i+1]) + " ");
			}
			else if (i == n - 1) {
				System.out.print(mean(prices[i-1], prices[i]));
			}
			else {
				System.out.print(mean(prices[i-1], prices[i], prices[i+1]) + " ");
			}
		}
	}
}
