package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int[] cost = new int [4];
			int[] uses = new int [12];
			
			st = new StringTokenizer(br.readLine());
			for(int i =0; i <4; i++	) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i =0; i <12; i++	) {
				uses[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = cost[3];
			
			int [] dp = new int [13];
			for(int i = 1; i <=12; i++){
				int temp = Math.min(dp[i-1]+uses[i-1] * cost[0],dp[i-1]+cost[1]);	//일일권이나 1달권 비교
				if(i >=3)
					temp = Math.min(temp,dp[i-3]+cost[2]);
				if(i==12){
					temp = Math.min(cost[3],temp);
				}
				dp[i] = temp;
			}
			result = dp[12];
			
			
			
			
			System.out.println("#" + tc+" "+ result);
		}
	}

}
