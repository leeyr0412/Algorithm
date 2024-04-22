import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int[][] feild;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			feild = new int[N][M];
			int k = Integer.parseInt(st.nextToken());
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				feild[n][m] = 1;
			}
			int result = 0;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (feild[y][x] == 1) {
						result++;
						search(y, x);
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	static void search(int y, int x) {
		feild[y][x] = 0;
		if (y + 1 < N && feild[y + 1][x] == 1) {
			search(y + 1, x);

		}
		if (y - 1 >= 0 && feild[y - 1][x] == 1) {
			search(y - 1, x);

		}
		if (x + 1 < M && feild[y][x + 1] == 1) {
			search(y, x + 1);
		}
		if (x - 1 >= 0 && feild[y][x - 1] == 1) {
			search(y, x - 1);
		}
	}
}