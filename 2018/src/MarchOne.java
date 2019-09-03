import java.util.Scanner;

// 201803-1���� : ��һ��

public class MarchOne {
	// ��¼��һ����Ծ��״̬,��1,2��ȡֵ
	private static int lastState = 1;
	// ��¼��һ����Ծ�ĵ÷�
	private static int lastScore = 0;
	// ��¼�ܵ÷�
	private static int sum = 0;
	
	// ���뱾����Ծ״̬,�ı�sum,���ʧ�ܷ���false
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
