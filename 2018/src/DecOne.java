import java.util.Scanner;

// 201812-1 С����ѧ
// �����������
public class DecOne {
	// ����·��ʹ�õ�ʱ��
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
	 * paras : kindΪ�ü�ʱ������
	 * time : һ��·���ѵ�ʱ����ߵƵĵ���ʱ
	 */
	public static int getTime(int kind, int time) {
		switch (kind) {
		case 0:
			return time;
		case 1:	// ���
			return time;
		case 2: //�Ƶ�
			return time + red;
		case 3: //�̵�
			return 0;
		default:
			return  -1;
		}
	}
}
