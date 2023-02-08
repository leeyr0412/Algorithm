package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D2_1954_달팽이숫자_이예리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] dt = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for(int tc = 1; tc <= T; tc++){
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];

            int i = 1;
            int limit = n*n;
            int d = 0;
            int r= 0, c=0;
            if(n == 1){
                System.out.println("#"+tc);
                System.out.println(1);
                continue;
            }
            while (i <= limit){
                arr[r][c] = i;
                /* 범위 검색 */
                if((r+dt[d][0])>=n || (r+dt[d][0])<0 ||(c+dt[d][1])>=n || (c+dt[d][1])<0){
                    d = (d+1)%4;
                }
                if(arr[r+dt[d][0]][c+dt[d][1]]!=0){
                    d = (d+1)%4;
                }
                r = r+dt[d][0];
                c = c+dt[d][1];

                i++;
            }
            System.out.println("#"+tc);
            for(int y = 0; y < n; y++){
                for(int x= 0; x < n; x++){
                    System.out.print(arr[y][x]+" ");
                }
                System.out.println();
            }
        }
    }
}
