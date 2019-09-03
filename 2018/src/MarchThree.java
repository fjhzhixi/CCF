import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//201803-3 URL映射

// path规则一定在最后,这会简化问题
public class MarchThree {
	

	public static void main(String[] args) {
		// 输入数据
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		// 输入规则
		ArrayList<Rule> rules = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String ruleStr = input.next();
			String ruleName = input.next();
			rules.add(new Rule(ruleName, ruleStr));
		}
		// 输入URL
		String[] urls = new String[m];
		for (int i = 0; i < m; i++) {
			urls[i] = input.next();
		}
		input.close();
		// 逐个进行匹配
		for (int i = 0; i < m; i++) {
			int j;
			for (j = 0; j < n; j++) {
				if (rules.get(j).isMatch(urls[i])) // 匹配成功
					break;
			}
			if (j == n) // 匹配失败
				System.out.println("404");
		}
	}
}

// 将所有的规则都转化为正则表达式形式
class Rule {
	// 规则的名字
	public String name;
	// 规则对应的正则表达式匹配器
	public Pattern pattern;
	 
	public Rule(String name, String str) {
		this.name = name;
		// 对字符表达形式的规则中的对应项进行替换
		String regex;
		regex = str.replaceAll("<int>", "(\\\\d+)");	//"\\d+"表示任意长度的非空整数
		regex = regex.replaceAll("<str>", "(\\\\w+)");	//"\\w+"表示任意长度的非空非\合法字符串
		regex = regex.replaceAll("<path>", "(.+)");	//.+表示匹配任意非空的合法字符串
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
