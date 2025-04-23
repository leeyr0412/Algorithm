import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] combi; // 조합
	static int com_num; // 조합 수
	static int[] numbers;
	static int[][] synergy;
	static int N;
	static int result = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		

		
//		int T = Integer.parseInt(br.readLine());
//		for (int tc = 1; tc <= T; tc++) {
			result  = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());

			/* 시너지 배열 초기화 */
			synergy = new int[N + 1][N + 1];
			for (int line = 1; line <= N; line++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= N; col++) {
					synergy[line][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			numbers = new int[N];

			com(0,2,N/2-1);
			System.out.println(result);
//			System.out.println("#"+tc+" "+ result);
//		}
	}

	private static void com(int cnt, int start, int M) {
		if (cnt == M) {
			int[] arr1 = new int [N/2];
			int[] arr2 = new int [N/2];
			arr1[0] = 1;
			int index1 = 1;
			int index2 = 0;
			int n = 0;
			for (int i = 2; i <= N; i++) {
				if(i == numbers[n])
					arr1[index1++] = numbers[n++];
				else
					arr2[index2++]=i;
			}
			int a = Math.abs((getTasty(arr1)-getTasty(arr2)));
			if(a<result)
				result = a;
			return;
		}
		for (int i = start; i <= N; i++) { // 인덱스 번호부터 대입
			numbers[cnt] = i;
			com(cnt + 1, i + 1, M);
		}

	}
	
	static int getTasty(int[] arr){
		int tasty = 0;
		for(int row : arr) {
			for(int col : arr) {
				tasty += synergy[row][col];
			}
		}
		return tasty;
	}
	

}
