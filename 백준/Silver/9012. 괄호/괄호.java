import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {

	static Stack<Character> stack;
	static HashMap<Character, Character> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		map.put(')', '(');
		for (int tc = 1; tc <= T; tc++) {
			int result = 1;
			String str = br.readLine();
			stack = new Stack<>();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c == ')') {
					if(stack.size()==0) {
						result = 0;
						break;
					}
					result = check(c);
					if (result == 0) {
						break;
					}
					stack.pop();
				} else {
					stack.push(c);
				}
			}

			if (stack.isEmpty() && result == 1)
				System.out.println("YES");
			else
				System.out.println("NO");
		}

	}

	private static int check(char c) {
		if (map.get(c) == stack.peek()) {
			return 1;
		}
		return 0;
	}

}
