import java.util.Scanner;

//201803-2
public class MarchTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		// 球的数目
		int n = input.nextInt();
		// 运动范围
		int l = input.nextInt();
		// 运动时间
		int t = input.nextInt();
		// 构建球的实例
		Ball[] balls = new Ball[n];
		for (int i = 0; i < n; i++) {
			// 初始方向全部向右
			balls[i] = new Ball(input.nextInt(), 1);
		}
		input.close();
		// 模拟球的运动
		for (int i = 0; i < t; i++) {
			// 运动一次
			for (int j = 0; j < n; j++) {
				balls[j].move();
			}
			// 判断有球是否到边界且运动方向向外,有则改变方向
			for (int j = 0; j < n; j++) {
				if (balls[j].isBound(l)) {
					balls[j].turn();
				}
			}
			// 判断是否有球相撞,有则双方转向
			for (int j = 0; j < n; j++) {
				for (int k = j; k < n; k++) {
					if (balls[j].getPosition() == balls[k].getPosition()) {
						balls[j].turn();
						balls[k].turn();
					}
				}
			}
		}
		// 输出最后结果
		for (int i = 0; i < n; i++) {
			if (i == n - 1)
				System.out.print(balls[i].getPosition());
			else
				System.out.print(balls[i].getPosition() + " ");
		}
	}
}

// 描述球的状态的类
class Ball {
	// 球所处的位置
	private int position;
	// 球运动的方向 : 1 为向右, -1为向左
	private int direction;
	
	public Ball(int p, int d) {
		this.position = p;
		this.direction = d;
	}
	
	// 球调转方向
	public void turn() {
		this.direction *= -1;
	}
	
	// 球运动一次
	public void move() {
		this.position += this.direction;
	}
	
	// 获得球的坐标
	public int getPosition() {
		return this.position;
	}
	
	//判断球是否到边界
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
