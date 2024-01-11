import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> stack = new Stack<>();
		String str = br.readLine();

		StringBuilder sb = new StringBuilder();

		for (char c : str.toCharArray()) {
			if (Character.isAlphabetic(c)) {	//연산기호 아니면 출력문장에 넣기
				sb.append(c);
			} else {		//연산자는 스택에 쌓기
				if (c == '(') {
					stack.push(c);
				} else if (c == '*' || c == '/') {
					while(!stack.isEmpty()&&(stack.peek()=='*'|| stack.peek()=='/')) {	//스택에 연산자 우선순위가 같거나 낮은게 있으면 팝
						sb.append(stack.pop());
					}
					stack.push(c);	//스택에 넣어주기
				}else if(c == '+' || c== '-') {	
					while(!stack.isEmpty() && stack.peek() !='(') {
						sb.append(stack.pop());
					}
					stack.push(c);
				}
				else {	//')' 들어오면 열리는 괄호까지 연산자 팝
					while(!stack.isEmpty() && stack.peek()!='(') {
						sb.append(stack.pop());
					}
					stack.pop(); 	//괄호 제거
				}
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}

}