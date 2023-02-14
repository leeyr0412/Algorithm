package yeri.algorithm0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_10828_스택 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			switch (st.nextToken()) {
			case "push":
				stack.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if (stack.size() != 0) {
					System.out.println(stack.pop());
				} else {
					System.out.println(-1);
				}
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				if (!stack.empty()) {
					System.out.println(0);
				} else {
					System.out.println(1);
				}
				break;
			case "top":
				if (stack.size() != 0) {
					System.out.println(stack.peek());
				} else {
					System.out.println(-1);
				}
				break;
			}
		}
	}

}
