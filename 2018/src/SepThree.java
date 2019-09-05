import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 201809-3元素选择器 : 实现CSS选择器
public class SepThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		ArrayList<Element> elements = new ArrayList<>();
		ArrayList<Selector> selectors = new ArrayList<>();
		// 输入结构化文本
		input.nextLine();
		for (int i = 0; i < n; i++) {
			String str = input.nextLine();
			elements.add(new Element(i + 1, str, elements));
		}
		// 输入选择器
		for (int i = 0; i < m; i++) {
			String str = input.nextLine();
			selectors.add(getSelector(str));
		}
		input.close();
		// 进行选择
		for (int i = 0; i < m; i++) {
			ArrayList<Element> choosed;
			choosed = selectors.get(i).select(elements);
			System.out.print(choosed.size() + " ");
			for (Element e : choosed) {
				System.out.print(e.num + " ");
			}
			System.out.println();
		}
	}	
	
	//生成选择器的工厂方法
	public static Selector getSelector(String str) {
		// 复合选择器
		if (str.contains(" ")) {
			return new complexSelector(str);
		}
		else {
			// id选择器
			if (str.startsWith("#")) {
				return new idSelector(str);
			}
			// label选择器
			else {
				return new labelSelector(str); 
			}
		}
	}
}

//构建每一行的元素结构
class Element {
	public int num;		//行号
	public String label;	//标签值注意要转化为小写
	public String id;
	public int rank;	//等级 = 点数 / 2
	public Element parent;	//记录它的父节点,即距离最近的rank-1节点
	public Element(int num, String str, ArrayList<Element> elements) {
		// 通过每一行的str生成结构化数据
		// 对str进行正则处理
		this.num = num;
		int count = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '.')
				count++;
			rank = count / 2;
		}
		String[] strs = str.substring(count).split(" ");
		if (strs.length == 1) {
			label = strs[0];
			id = "";
		}
		else {
			label = strs[0];
			id = strs[1].substring(1);
		}
		parent = null;
		if (rank != 0) {
			// 不是根节点
			for (int i = elements.size() - 1; i >= 0; i--) {
				if (elements.get(i).rank == rank - 1) {
					parent = elements.get(i);
					break;
				}
			}
		}
	}
	
	// 输入的只能是单选择器,判断该元素父结构是否满足要求
	public boolean isParentMatch(ArrayList<Selector> selectors) {
		// 使用递归求解
		int num = selectors.size();
		boolean flag = false;
		Element parentE = parent;
		Selector s = selectors.get(num - 1);
		while (parentE != null) {
			if (s.isMatch(parentE)) {
				flag = true;
				break;
			}
			parentE = parentE.parent;
		}
		if (!flag) {
			return false;
		}
		else {
			if (num == 1)
				return true;
			else {
				//递归查找
				return parentE.isParentMatch(removeLast(selectors));
			}
		}
	}
	public static ArrayList<Selector> removeLast(ArrayList<Selector> s) {
		ArrayList<Selector> newS = new ArrayList<>();
		newS.addAll(s);
		newS.remove(newS.size() - 1);
		return newS;
	}
}

// 构建选择器
abstract class Selector {
	public ArrayList<Element> select(ArrayList<Element> elements) {
		ArrayList<Element> choosed = new ArrayList<>();
		for (Element e : elements) {
			if (isMatch(e)) {
				choosed.add(e);
			}
		}
		return choosed;
	}
	public abstract boolean isMatch(Element e);
}

// 标签选择器
class labelSelector extends Selector {
	public String label;
	public labelSelector(String str) {
		label = str.toLowerCase();
	}
	
	@Override
	public boolean isMatch(Element e) {
		// TODO Auto-generated method stub
		if (e.label.equals(label))
			return true;
		else 
			return false;
	}
}

// id选择器
class idSelector extends Selector {
	public String id;
	public idSelector(String str) {
		id = str.substring(1);
	}
	@Override
	public boolean isMatch(Element e) {
		// TODO Auto-generated method stub
		if (e.id.equals(id))
			return true;
		else 
			return false;
	}
}

// 复合选择器
/*
 * 从最后一个开始选择,之后依次向上选择
 */
class complexSelector extends Selector {
	public ArrayList<Selector> selectors;
	public complexSelector(String str) {
		selectors = new ArrayList<>();
		String [] strs = str.split(" ");
		for (String s : strs) {
			if (s.startsWith("#")) {
				selectors.add(new idSelector(s));
			}
			// label选择器
			else {
				selectors.add(new labelSelector(s));
			}
		}
	}
	
	@Override
	public ArrayList<Element> select(ArrayList<Element> elements) {
		// TODO Auto-generated method stub
		ArrayList<Element> choosed = new ArrayList<>();
		
		// 先用最后一个筛选出基本的
		Selector selector = selectors.get(selectors.size() - 1);
		choosed = selector.select(elements);

		// 在根据之前的选择器删除不符合的节点
		for (int i = selectors.size() - 2; i >= 0; i--) {
			if (choosed.size() == 0)
				break;
			for (int j = 0; j < choosed.size(); j++) {
				if (!choosed.get(j).isParentMatch(Element.removeLast(selectors))) {
					choosed.remove(j);
					j--;
				}
			}
		}
		return choosed;
	}
	
	@Override
	public boolean isMatch(Element e) {
		// TODO Auto-generated method stub
		return false;
	}
}






