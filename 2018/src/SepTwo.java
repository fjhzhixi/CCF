import java.util.Scanner;

// 201809-2 ���
// ����Ϊ���������󽻼��㷨
/*�������
 * ��ʱ���Ϊt[],��A��ʱ���+1,��B��ʱ���+1,���ͳ��Ϊ2�ĸ���,
 * ע�� : ���俪ͷ���β����
 */
public class SepTwo {
	// ʱ������
	public static int[] time = new int[1000000];
	// �����˵���������
	public static int[][] aSections = new int [2000][2];
	public static int[][] bSections = new int [2000][2];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int aMax = 0;
		int bMax = 0;
		for (int i = 0; i < n; i++) {
			int min = input.nextInt();
			int max = input.nextInt();
			aSections[i][0] = min;
			aSections[i][1] = max;
			if (i == n - 1)
				aMax = max;
		}
		for (int i = 0; i < n; i++) {
			int min = input.nextInt();
			int max = input.nextInt();
			bSections[i][0] = min;
			bSections[i][1] = max;
			if (i == n - 1) 
				bMax = max;
		}
		int length = Math.max(aMax, bMax);
		mark(aSections, n);
		mark(bSections, n);
		System.out.print(sum(length));
	}
	
	public static void mark(int [][] sections, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = sections[i][0]; j < sections[i][1]; j++) {
				time[j] ++;
			}
		}
	}
	
	// ͳ���ظ�������
	public static int sum(int length) {
		int s = 0;
		for (int i = 0; i < length; i++) {
			if (time[i] == 2)
				s++;
		}
		return s;
	}

}
