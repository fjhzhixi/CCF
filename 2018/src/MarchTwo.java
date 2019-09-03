import java.util.Scanner;

//201803-2
public class MarchTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		// �����Ŀ
		int n = input.nextInt();
		// �˶���Χ
		int l = input.nextInt();
		// �˶�ʱ��
		int t = input.nextInt();
		// �������ʵ��
		Ball[] balls = new Ball[n];
		for (int i = 0; i < n; i++) {
			// ��ʼ����ȫ������
			balls[i] = new Ball(input.nextInt(), 1);
		}
		input.close();
		// ģ������˶�
		for (int i = 0; i < t; i++) {
			// �˶�һ��
			for (int j = 0; j < n; j++) {
				balls[j].move();
			}
			// �ж������Ƿ񵽱߽����˶���������,����ı䷽��
			for (int j = 0; j < n; j++) {
				if (balls[j].isBound(l)) {
					balls[j].turn();
				}
			}
			// �ж��Ƿ�������ײ,����˫��ת��
			for (int j = 0; j < n; j++) {
				for (int k = j; k < n; k++) {
					if (balls[j].getPosition() == balls[k].getPosition()) {
						balls[j].turn();
						balls[k].turn();
					}
				}
			}
		}
		// ��������
		for (int i = 0; i < n; i++) {
			if (i == n - 1)
				System.out.print(balls[i].getPosition());
			else
				System.out.print(balls[i].getPosition() + " ");
		}
	}
}

// �������״̬����
class Ball {
	// ��������λ��
	private int position;
	// ���˶��ķ��� : 1 Ϊ����, -1Ϊ����
	private int direction;
	
	public Ball(int p, int d) {
		this.position = p;
		this.direction = d;
	}
	
	// ���ת����
	public void turn() {
		this.direction *= -1;
	}
	
	// ���˶�һ��
	public void move() {
		this.position += this.direction;
	}
	
	// ����������
	public int getPosition() {
		return this.position;
	}
	
	//�ж����Ƿ񵽱߽�
	public Boolean isBound(int L) {
		if ((position == 0 && direction == -1) || 
				(position == L && direction == 1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
