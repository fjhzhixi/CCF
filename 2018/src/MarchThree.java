import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//201803-3 URLӳ��

// path����һ�������,��������
public class MarchThree {
	

	public static void main(String[] args) {
		// ��������
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		// �������
		ArrayList<Rule> rules = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String ruleStr = input.next();
			String ruleName = input.next();
			rules.add(new Rule(ruleName, ruleStr));
		}
		// ����URL
		String[] urls = new String[m];
		for (int i = 0; i < m; i++) {
			urls[i] = input.next();
		}
		input.close();
		// �������ƥ��
		for (int i = 0; i < m; i++) {
			int j;
			for (j = 0; j < n; j++) {
				if (rules.get(j).isMatch(urls[i])) // ƥ��ɹ�
					break;
			}
			if (j == n) // ƥ��ʧ��
				System.out.println("404");
		}
	}
}

// �����еĹ���ת��Ϊ������ʽ��ʽ
class Rule {
	// ���������
	public String name;
	// �����Ӧ��������ʽƥ����
	public Pattern pattern;
	 
	public Rule(String name, String str) {
		this.name = name;
		// ���ַ������ʽ�Ĺ����еĶ�Ӧ������滻
		String regex;
		regex = str.replaceAll("<int>", "(\\\\d+)");	//"\\d+"��ʾ���ⳤ�ȵķǿ�����
		regex = regex.replaceAll("<str>", "(\\\\w+)");	//"\\w+"��ʾ���ⳤ�ȵķǿշ�\�Ϸ��ַ���
		regex = regex.replaceAll("<path>", "(.+)");	//.+��ʾƥ������ǿյĺϷ��ַ���
		regex = "^" + regex + "$";
		//System.out.println(regex);
		pattern = Pattern.compile(regex);
	}
	
	public boolean isMatch(String str) {
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			System.out.print(name + " ");
			for (int i = 1; i <= matcher.groupCount(); i++) {
				String matchStr = matcher.group(i);
				if (isNum(matchStr)) {
					System.out.print(Integer.valueOf(matchStr) + " ");
				}
				else {
					System.out.print(matchStr + " ");
				}
			}
			System.out.println();
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isNum(String str) {
		Pattern pattern = Pattern.compile("^//d+$");
		return pattern.matcher(str).matches();
	}
}
