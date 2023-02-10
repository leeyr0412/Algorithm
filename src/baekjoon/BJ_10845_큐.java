package yeri.algorithm0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_10845_큐 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> que = new ArrayDeque<>(); 
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "push":
				que.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if(que.size()!=0) {
					System.out.println(que.poll());
					que.remove(0);
				}else {
					System.out.println(-1);
				}
				break;
			case "size":
				System.out.println(que.size());
				break;
			case "empty":
				if(que.size()!=0) {
					System.out.println(0);
				}else {
					System.out.println(1);
				}
				break;
			case "front":
				if(que.size()!=0) {
					System.out.println(que.peekFirst());
				}else {
					System.out.println(-1);
				}
				break;
			case "back":
				if(que.size()!=0) {
					System.out.println(que.peekLast());
				}else {
					System.out.println(-1);
				}
				break;
			}
		}
	}
}
