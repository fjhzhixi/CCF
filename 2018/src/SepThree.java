import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 201809-3Ԫ��ѡ���� : ʵ��CSSѡ����
public class SepThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		ArrayList<Element> elements = new ArrayList<>();
		ArrayList<Selector> selectors = new ArrayList<>();
		// ����ṹ���ı�
		input.nextLine();
		for (int i = 0; i < n; i++) {
			String str = input.nextLine();
			elements.add(new Element(i + 1, str, elements));
		}
		// ����ѡ����
		for (int i = 0; i < m; i++) {
			String str = input.nextLine();
			selectors.add(getSelector(str));
		}
		input.close();
		// ����ѡ��
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
	
	//����ѡ�����Ĺ�������
	public static Selector getSelector(String str) {
		// ����ѡ����
		if (str.contains(" ")) {
			return new complexSelector(str);
		}
		else {
			// idѡ����
			if (str.startsWith("#")) {
				return new idSelector(str);
			}
			// labelѡ����
			else {
				return new labelSelector(str); 
			}
		}
	}
}

//����ÿһ�е�Ԫ�ؽṹ
class Element {
	public int num;		//�к�
	public String label;	//��ǩֵע��Ҫת��ΪСд
	public String id;
	public int rank;	//�ȼ� = ���� / 2
	public Element parent;	//��¼���ĸ��ڵ�,�����������rank-1�ڵ�
	public Element(int num, String str, ArrayList<Element> elements) {
		// ͨ��ÿһ�е�str���ɽṹ������
		// ��str����������
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
			// ���Ǹ��ڵ�
			for (int i = elements.size() - 1; i >= 0; i--) {
				if (elements.get(i).rank == rank - 1) {
					parent = elements.get(i);
					break;
				}
			}
		}
	}
	
	// �����ֻ���ǵ�ѡ����,�жϸ�Ԫ�ظ��ṹ�Ƿ�����Ҫ��
	public boolean isParentMatch(ArrayList<Selector> selectors) {
		// ʹ�õݹ����
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
				//�ݹ����
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

// ����ѡ����
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

// ��ǩѡ����
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

// idѡ����
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

// ����ѡ����
/*
 * �����һ����ʼѡ��,֮����������ѡ��
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
			// labelѡ����
			else {
				selectors.add(new labelSelector(s));
			}
		}
	}
	
	@Override
	public ArrayList<Element> select(ArrayList<Element> elements) {
		// TODO Auto-generated method stub
		ArrayList<Element> choosed = new ArrayList<>();
		
		// �������һ��ɸѡ��������
		Selector selector = selectors.get(selectors.size() - 1);
		choosed = selector.select(elements);

		// �ڸ���֮ǰ��ѡ����ɾ�������ϵĽڵ�
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






