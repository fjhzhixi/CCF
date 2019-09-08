import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// 201812-3 CIDR合并
// 使用kruskal算法
public class DecThree {
	public final static int MAX = 500000;
	public static int father[] = new int[MAX];
	public static int m;	//边数
	public static int n;	//顶点数
	public static ArrayList<Edge> edges = new ArrayList<>();	//边的集合
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
	// 支持并查集 + 路径压缩的方法
	public static void init() {
		//对father全部初始化为自身
		for (int i = 0; i < n; i++) {
			father[i] = i;
		}
	}
	
	public static int find(int x) {
		// 查找生成树集合代表
		//return father[n] == n ? n : find(father[n]);
		int a = x;
	    while (x != father[x]) {
		x = father[x];
	    }
	    //路径压缩
	    while (a != father[a]) {
		int z = a;
		a = father[a];
		father[z] = x;
	    }
	    return x;
	}
	 
	public static boolean merge(int x, int y) {
		// 合并两个在一个联通树上的节点返回true
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			father[fx] = fy;
			return true;
		}
		else
			return false;
	}
	
	//kruskal算法
	/*
	 * 1.新建一张图,有原图的n个节点,但是没有边
	 * 2.将原图的边按权值排列
	 * 3.按权值大小遍历边直到所有节点在一个生成树(连通分量)上
	 *		即 : 两个点不在统一连通分量,则加入这条边
	 */
	public static int kruskal() {
		// 建立最小生成树,返回生成树中的最大权值边
		int max = 0;
		init();
		// 对边进行排序
		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
		});
		// 建立最小连通树
		for (Edge e : edges) {
			if (merge(e.u, e.v)) {
				// 如果这两个点没有连通
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
