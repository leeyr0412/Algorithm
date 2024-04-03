import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] gears = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j]=str.charAt(j) - '0';
            }
        }

        int[] d = new int[4];
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int gear = Integer.parseInt(st.nextToken()) - 1;
            int course = Integer.parseInt(st.nextToken());
            int [] index = new int[4];
            index[gear]= course*(-1);
            
            //왼쪽으로 검사
            for (int g = gear; g > 0; g--) {
                if (gears[g][(6+d[g])%8] != gears[g - 1][(2+d[g-1])%8]) {
                    index[g - 1] += index[g] * (-1);
                } else break;
            }
            //오른쪽으로 검사
            for (int g = gear; g < 3; g++) {
                if (gears[g][(2+d[g])%8]!=gears[g + 1][(6+d[g+1])%8]) {
                    index[g + 1] += index[g] * (-1);
                } else break;
            }

            //돌려
            for(int g = 0; g<4;g++)  {
                d[g]+=index[g];
                d[g] = d[g] < 0 ? 7 : d[g];
            }
        }

        int score = gears[0][d[0]%8];
        score+=gears[1][d[1]%8]*2;
        score+=gears[2][d[2]%8]*4;
        score+=gears[3][d[3]%8]*8;
        System.out.println(score);
    }
}