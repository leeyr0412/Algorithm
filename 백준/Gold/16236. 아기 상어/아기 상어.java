import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos implements Comparable<Pos> {
	int r, c, count;

	public Pos(int r, int c, int count) {
		super();
		this.r = r;
		this.c = c;
		this.count = count;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(Pos o) {
		if (this.count > o.count) {
			return 1;
		} else if (this.count == o.count) {
			if (this.r > o.r) {
				return 1;
			} else if (this.r == o.r) {
				return this.c - o.c;
			} else {
				return -1;
			}
		}
		return -1;
	}

}

public class Main {

	static int[][] map;
	static int[][] dr = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N;
	static int[] curr = new int[2]; // 상어 위치
	static int currSize = 2;// 상어 크기
	static int eatCount = 0;// 상어 크기
//if (newR < R && newR >= 0 && newC < C && newC >= 0) {// 범위검사
	/*
	 * int newR = r + d[0]; int newC = c + d[1];
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine());

		int answer = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					curr[0] = r;
					curr[1] = c;
					map[r][c] = 0;
				}
			}
		}
		while (true) {
			// 먹을 수 있는 물고기 있는지 검사
//			if (help()) {
//				break;
//			}

			// 물고기 먹으러 감
			int time = findFish();

//			print();

			if (time < 0)
				break;
			answer += time;
			if (eatCount == currSize) {
				currSize++;
				eatCount = 0;
			}
		}

		System.out.println(answer);
	}

//	private static void print() {
//		System.out.println("==============================================");
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				System.out.printf("%d ",map[r][c]);
//			}
//			System.out.println();
//		}
//	}

	private static int findFish() {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Pos> que = new PriorityQueue<>();
		que.add(new Pos(curr[0], curr[1], 0));
		visited[curr[0]][curr[1]] = true;
		while (!que.isEmpty()) {
			Pos now = que.poll();
			if (map[now.r][now.c] > 0) {
				if (map[now.r][now.c] < currSize) { // 먹을 수 있음
					eatCount++; // 먹은 물고기 수 증가
					curr[0] = now.r; // 현재 좌표 이동
					curr[1] = now.c;
					map[now.r][now.c] = 0; // 물고기 먹었음
					return now.count;
				}
			}
			for (int[] d : dr) {
				int newR = now.r + d[0];
				int newC = now.c + d[1];
				if (newR < N && newR >= 0 && newC < N && newC >= 0) {// 범위검사
					if (map[newR][newC] > currSize || visited[newR][newC]) {
						continue;
					}
					visited[newR][newC] = true;
					que.add(new Pos(newR, newC, now.count + 1));
				}
			}
		}

		return -1;
	}

}

/**
 * 먹을 수 있는 물고기 있으면 false 아니면 도움청함
 * 
 * @return
 */
//	private static boolean help() {
//		for(int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				if(map[r][c]>0) {
//					if(map[r][c] <currSize) {
//						return false;
//					}
//				}
//			}
//		}
//		return true;
//	}
//}