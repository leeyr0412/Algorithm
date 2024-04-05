import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/* 전진하는 방향 */
	static int[][] D = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	/* 지도 */
	static int[][] map;
	static int N;
	static int M;
	/* 현재 위치, 바라보는 방향 */
	static int currY;
	static int currX;
	static int currD;
	/* 청소 한 곳 표시 */
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		currY = Integer.parseInt(st.nextToken());
		currX = Integer.parseInt(st.nextToken());
		currD = Integer.parseInt(st.nextToken());

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		int count = 0;
		while (true) {
			/* 청소 */
			if (!visited[currY][currX]) {
				visited[currY][currX] = true;
				count++;
			}
			/* 주변에 청소 안된 칸 있음 */
			if (search(currY, currX)) {
				while (true) {
					/* 회전 */
					currD = (currD - 1 >= 0) ? (currD - 1) : 3;
					/* 바라보는 방향이 청소 안된 칸이면 이동 */
					if (map[currY + D[currD][0]][currX + D[currD][1]] == 0
							&& !visited[currY + D[currD][0]][currX + D[currD][1]]) {
						currY = currY + D[currD][0];
						currX = currX + D[currD][1];
						break;
					}
				}
			} else {
				/* 뒤가 벽 */
				if (map[currY + D[(currD + 2) % 4][0]][currX + D[(currD + 2) % 4][1]] == 1) {
					System.out.println(count);
					return;
				}
				/* 후진 가능 */
				currY = currY + D[(currD + 2) % 4][0];
				currX = currX + D[(currD + 2) % 4][1];
			}
		}
	}

	/* 주위 빈 칸 있는지 확인 */
	private static boolean search(int r, int c) {
		if (r - 1 >= 0) {
			if (map[r - 1][c] == 0 && visited[r - 1][c] == false) {
				return true;
			}
		}
		if (c - 1 >= 0) {
			if (map[r][c - 1] == 0 && visited[r][c - 1] == false) {
				return true;
			}
		}
		if (r + 1 < N) {
			if (map[r + 1][c] == 0 && visited[r + 1][c] == false) {
				return true;
			}
		}
		if (c + 1 < M) {
			if (map[r][c + 1] == 0 && visited[r][c + 1] == false) {
				return true;
			}
		}
		return false;
	}
}