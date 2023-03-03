import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos {
	private int r, c;

	public Pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

}

public class Solution {

	static int[][] map;
	static int[][] dr = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N;
	static int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				String line = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = line.charAt(c) - '0';
				}
			}

			answer=bfs();

			System.out.println("#"+tc+" "+answer);
		}

	}

	private static int bfs() {
		int[][] visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], INF);	//초기값 대입
		}
		Deque<Pos> que = new ArrayDeque<>();
		que.add(new Pos(0, 0));
		visited[0][0] = 0;
		while (!que.isEmpty()) {
			Pos now = que.poll();
			for (int[] d : dr) {
				int newR = now.getR() + d[0];
				int newC = now.getC() + d[1];
				if (newR < N && newR >= 0 && newC < N && newC >= 0) {// 범위검사
					if (visited[newR][newC] > visited[now.getR()][now.getC()] + map[newR][newC]) {	//기존 위치보다 작은 값이면 큐에 넣기
						visited[newR][newC] = visited[now.getR()][now.getC()] + map[newR][newC];
						que.add(new Pos(newR, newC));
					}
				}
			}
		}
		return visited[N - 1][N - 1];
	}
}