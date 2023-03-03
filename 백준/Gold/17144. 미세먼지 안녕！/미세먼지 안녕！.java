import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int[][] dr = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int machine = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) {
					continue;
				} else if (map[r][c] > 0) { // 미세먼지 있는곳
//					que.add(new int[] { r, c });
				} else { // 청정기
					machine = r;
				}
			}
		}
		machine--; // 청정기 좌표 윗칸
		
//		printMap(R,C);

		for (int t = 0; t < T; t++) {
			// 미세먼지 확산
			// 먼지 찾기
			int[][] newMap = new int[R][C];
			newMap[machine][0] = -1;
			newMap[machine+1][0] = -1;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) { // 먼지 찾았음
						int cnt = 0; // 확산 방향 수
						int sp = map[r][c] / 5;
						for (int[] d : dr) {
							int newR = r + d[0];
							int newC = c + d[1];
							if (newR < R && newR >= 0 && newC < C && newC >= 0) {// 범위검사
								if (map[newR][newC] != -1) {
									newMap[newR][newC] += sp;
									cnt++;
								}
							}
						}
						newMap[r][c] += map[r][c] - sp * cnt;
					}
				}
			}
			// 확산된 맵으로 카피
			for (int r = 0; r < R; r++) {
				map[r] = newMap[r].clone();
			}
			
//			printMap(R,C);

			// 공기청정기 작동
			Deque<Integer> que = new ArrayDeque<>(); // 미세먼지 넣을 거임
			for (int r = machine - 1; r >= 0; r--) { // 위 사이클
				que.add(map[r][0]);
			}
			for (int c = 1; c < C; c++) {
				que.add(map[0][c]);
			}
			for (int r = 1; r <= machine; r++) { 
				que.add(map[r][C-1]);
			}
			for (int c = C-2; c > 0; c--) {
				que.add(map[machine][c]);
			}
			que.poll();
			que.add(0);
			for (int r = machine - 1; r >= 0; r--) { 
				map[r][0] = que.poll();
			}
			for (int c = 1; c < C; c++) {
				map[0][c] = que.poll();
			}
			for (int r = 1; r <= machine; r++) { 
				map[r][C-1]= que.poll();
			}
			for (int c = C-2; c > 0; c--) {
				map[machine][c] = que.poll();
			}
			
			
			// 아래 사이클
			for (int r = machine + 2; r < R; r++) { 
				que.add(map[r][0]);
			}
			for (int c = 1; c < C; c++) {
				que.add(map[R-1][c]);
			}
			for (int r = R-2; r > machine; r--) { 
				que.add(map[r][C-1]);
			}
			for (int c = C-2; c > 0; c--) {
				que.add(map[machine+1][c]);
			}
			que.poll();
			que.add(0);
			for (int r = machine + 2; r < R; r++) { 
				map[r][0] = que.poll();
			}
			for (int c = 1; c < C; c++) {
				map[R-1][c] = que.poll();
			}
			for (int r = R-2; r > machine; r--) { 
				map[r][C-1] = que.poll();
			}
			for (int c = C-2; c > 0; c--) {
				map[machine+1][c] = que.poll();
			}
			
//			printMap(R,C);
		}

		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] > 0) { 
					answer += map[r][c];
				}
			}
		}
		System.out.println(answer);
	}

//	private static void printMap(int R, int C) {
//		for (int r = 0; r < R; r++) {
//			for (int c = 0; c < C; c++) {
//				System.out.printf("%2d ",map[r][c]);
//			}
//			System.out.println();
//		}
//		System.out.println("===========================");
//	}

	
}