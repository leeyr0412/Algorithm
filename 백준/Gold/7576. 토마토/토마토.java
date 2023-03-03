import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		Deque<int[]> que = new ArrayDeque<int[]>();
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					que.add(new int[] { r, c });
				}
			}
		}

		int[][] dr = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

		int max = 1;
		while (!que.isEmpty()) {
			int[] pos = que.poll();

			for (int[] d : dr) {
				int nr = pos[0] + d[0];
				int nc = pos[1] + d[1];
				if (nr < R && nr >= 0 && nc < C && nc >= 0) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = map[pos[0]][pos[1]] + 1;
						max = Math.max(max, map[nr][nc]);
						que.add(new int[] { nr, nc });
					}
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(max-1);
	}

}