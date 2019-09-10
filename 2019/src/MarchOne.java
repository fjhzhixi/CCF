import java.util.Scanner;

// 201909-1 С�д�
// ʹ�ù鲢����
public class MarchOne {
	public final static int MAX = 100000;
	public static int[] sorted = new int[MAX];	//�������������
	public static int n;	// ������Ŀ
	public static boolean ascOrder = true;	//Ϊtrue��Ϊ����(Ĭ��)
	public static double mid; //��λ��
	public static int max; //���ֵ
	public static int min; //��Сֵ
	
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
		//�����ֵ,��λ��,��Сֵ˳�����
		System.out.print(max + " ");
		if ((mid - (int)mid) == 0) {	//��λ��Ϊ����
			System.out.print((int)mid + " ");
		}
		else {
			System.out.printf("%.1f", mid);
			System.out.print(" ");
			
		}
		System.out.print(min);
	}
	
	public static void checkOrder() {
		// ��������������ǽ���
		for (int i = 0; i < n-1; i++) {
			if (sorted[i] > sorted[i+1]) {
				ascOrder = false;
				break;
			}
		}
	}
	
	public static void analyse() {
		// ͳ������
		max = ascOrder ? sorted[n-1] : sorted[0];
		min = ascOrder ? sorted[0] : sorted[n-1];
		mid = (n%2 == 0) ? (sorted[n/2 - 1] + sorted[n/2]) / 2.0 : (float)(sorted[(n-1)/2]); 
	}

}
