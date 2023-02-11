package ws.ws0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_16236_아기상어_이예리 {

	static int[][] arr;
	static boolean[][] visited;
	static boolean[][] queList;
//	static int currY;
//	static int currX;
	static Integer[] curr = new Integer[2];
	static int size = 2;
	static int eat = 0;
	static int result = 0;
	static int locY = 0;
	static int N;
	static int locX = 0;
	static LinkedList<Integer[]> que = new LinkedList<>();
	static int[][] go = {{-1,0}, {0,-1}, {0,1}, {1, 0}};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		arr = new int[N][N];
		visited = new boolean[N][N];
		queList = new boolean[N][N];
		
		/* 공간정보 받아옴 */
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < N; x++) {
				int t = Integer.parseInt(st.nextToken());
				arr[y][x] = t;
				if(t == 9) {
					curr[1] = x;
					locX = x;
					curr[0] = y;
					locY = y;
//					arr[y][x] = 0;
				}
			}
		}
		
		que.add(curr);
//		visited[locY][locX]=true;
		bfs(curr,0);
		System.out.println(result);
	}

	private static void bfs(Integer[] c, int cnt) {
		que.remove(0);
//		if(visited[c[0]][c[1]]) {
//			return;
//		}
		visited[c[0]][c[1]] = true;
		if(arr[c[0]][c[1]] == 9) {
			arr[c[0]][c[1]] = 0;
		}
		else if(arr[c[0]][c[1]]!=0&&arr[c[0]][c[1]]<size) {
			arr[c[0]][c[1]] = 0;
			eat++;
			if(eat == size) {
				size++;
				eat = 0;
				visited = new boolean[N][N];
			}
			result += cnt;
//			result += (Math.abs(c[1]-locX)+Math.abs(c[0]-locY));
			locX = c[1];
			locY = c[0];
		}
		
		for(int i = 0; i < 4; i++) {
			if(c[0]+go[i][0]< 0 || c[0]+go[i][0] >= N || c[1]+go[i][1] >= N || c[1]+go[i][1] < 0) {
				continue;
			}
			if(visited[c[0]+go[i][0]][c[1]+go[i][1]]) {
				continue;
			}
			if(arr[c[0]+go[i][0]][c[1]+go[i][1]]>size) {
				continue;
			}
			Integer[] newC = {c[0]+go[i][0],c[1]+go[i][1]};
//			int newX = c[0]+go[i][1];
//			if(que.indexOf(newC)<0)
			if(!queList[c[0]+go[i][0]][c[1]+go[i][1]]){
				que.add(newC);	
				queList[c[0]+go[i][0]][c[1]+go[i][1]] =true;
			}
//			que.add([c[0]+go[i][0]][c[1]+go[i][1]])
		}
		if(que.size() != 0)
			bfs(que.get(0), cnt+1);
		
//		result++;
		
	}

}
