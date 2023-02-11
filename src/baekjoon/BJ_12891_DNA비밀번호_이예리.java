package ws.ws0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12891_DNA비밀번호_이예리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		String DNA = br.readLine();
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		int result = 0;
		int countA, countC, countG, countT;
		for (int i = 0; i <= S - P; i++) {
			countA = 0;
			countC = 0;
			countG = 0;
			countT = 0;
			for (int j = i; j < i + P; j++) {
				switch (DNA.charAt(j)) {
				case 'A':
					countA++;
					break;
				case 'C':
					countC++;
					break;
				case 'G':
					countG++;
					break;
				case 'T':
					countT++;
					break;
				}
				if (countA >= A && countC >= C && countG >= G && countT >= T) {
					result++;
					break;
				}
			}
		}
		System.out.println(result);
	}

}
