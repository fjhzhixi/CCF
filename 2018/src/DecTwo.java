import java.util.Scanner;

public class DecTwo {
	public static TimeSpend[] timespends = new TimeSpend[100000];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Light.rTime = input.nextInt();
		Light.yTime = input.nextInt();
		Light.gTime = input.nextInt();
		int n = input.nextInt();
		for(int i = 0;i < n; i++) {
			int k = input.nextInt();
			int t = input.nextInt();
			if (k == 0) {
				timespends[i] = new Road(t);
			}
			else {
				timespends[i] = new Light(k, t);
			}
		}
		input.close();
		// 模拟时间尺度发生的过程
		long sum = 0;
		for (int i = 0 ; i < n; i++) {
			sum = timespends[i].getTime(sum);
			//System.out.println(sum);
		}
		System.out.print(sum);
	}
}

abstract class TimeSpend {
	public abstract long getTime(long sum);
}

class Road extends TimeSpend {
	public long time;
	public Road(long time) {
		this.time = time;
	}
	
	@Override
	public long getTime(long sum) {
		// TODO Auto-generated method stub
		return sum + time;
	}
}

class Light extends TimeSpend {
	public static long rTime;
	public static long yTime;
	public static long gTime;
	// 描述灯当前状态
	public int kind;	//红黄蓝
	public long remind;	//倒计时
	public Light(int kind, long remind) {	//初始状态
		this.kind = kind;
		this.remind = remind;
	}
	
	// 经过length时长之后状态的更新
	public void updata(long length) {
		if (length - remind < 0) {
			remind = remind - length;
			return;
		}
		else {
			long i = length - remind;
			kind = getNext(kind);
			while(i > getLength(kind)) {
				i -= getLength(kind);
				kind  = getNext(kind);
			}
			remind = getLength(kind) - i;
		}
		//System.out.println("this light is " + kind + " remind is " + remind );
	}
	
	public long getTime(long sum) {
		updata(sum);
		switch (kind) {
		case 1:	// 红灯
			return sum + remind;
		case 2: //黄灯
			return sum + remind + rTime;
		case 3: //绿灯
			return sum;
		default:
			return  -1;
		}
	}
	
	private int getNext(int k) {
		switch (k) {
		case 1:
			return 3;
		case 2:
			return 1;
		case 3:
			return 2;
		default:
			return -1;
		}
	}
	
	private long getLength(int k) {
		switch (k) {
		case 1:
			return rTime;
		case 2:
			return yTime;
		case 3:
			return gTime;
		default:
			return -1;
		}
	}
}
