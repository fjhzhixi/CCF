import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// 201812-3 CIDR�ϲ�
// ʹ��kruskal�㷨
public class DecThree {
	public final static int MAX = 500000;
	public static int father[] = new int[MAX];
	public static int m;	//����
	public static int n;	//������
	public static ArrayList<Edge> edges = new ArrayList<>();	//�ߵļ���
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		int root = input.nextInt();
		for (int i = 0; i < m; i++) {
			int u = input.nextInt();
			int v = input.nextInt();
			int w = input.nextInt();
			edges.add(new Edge(u, v, w));
		}
		input.close();
		System.out.print(kruskal());
		
	}
	// ֧�ֲ��鼯 + ·��ѹ���ķ���
	public static void init() {
		//��fatherȫ����ʼ��Ϊ����
		for (int i = 0; i < n; i++) {
			father[i] = i;
		}
	}
	
	public static int find(int x) {
		// �������������ϴ���
		//return father[n] == n ? n : find(father[n]);
		int a = x;
	    while (x != father[x]) {
		x = father[x];
	    }
	    //·��ѹ��
	    while (a != father[a]) {
		int z = a;
		a = father[a];
		father[z] = x;
	    }
	    return x;
	}
	 
	public static boolean merge(int x, int y) {
		// �ϲ�������һ����ͨ���ϵĽڵ㷵��true
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			father[fx] = fy;
			return true;
		}
		else
			return false;
	}
	
	//kruskal�㷨
	/*
	 * 1.�½�һ��ͼ,��ԭͼ��n���ڵ�,����û�б�
	 * 2.��ԭͼ�ı߰�Ȩֵ����
	 * 3.��Ȩֵ��С������ֱ�����нڵ���һ��������(��ͨ����)��
	 *		�� : �����㲻��ͳһ��ͨ����,�����������
	 */
	public static int kruskal() {
		// ������С������,�����������е����Ȩֵ��
		int max = 0;
		init();
		// �Ա߽�������
		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
		});
		// ������С��ͨ��
		for (Edge e : edges) {
			if (merge(e.u, e.v)) {
				// �����������û����ͨ
				if (max < e.weight)
					max = e.weight;
			}
		}
		return max;
	}
}
class Edge {
	public int u;
	public int v;
	public int weight;
	public Edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.weight = w;
	}
}
