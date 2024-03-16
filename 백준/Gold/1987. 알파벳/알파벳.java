import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] alpha = new int[26];
    static char[][] board;
    static int result=0;
    static int count=0;
    static int R=0;
    static int C=0;
    static int[][] dr = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         R = Integer.parseInt(st.nextToken());
         C = Integer.parseInt(st.nextToken());
         board = new char[R][C];

        for (int line = 0; line < R; line++) {
            String str = br.readLine();
            for (int cha = 0; cha < C; cha++) {
                board[line][cha] = str.charAt(cha);
            }
        }
        dfs(0,0);
        System.out.println(result);
    }

    private static void dfs(int r, int c) {
        alpha[board[r][c] - 'A'] ++;
        count++;
        if(count>result) result = count;
        for(int[] d:dr){
            int newR = r+d[0];
            int newC = c+d[1];
            //범위검사
            if(newR<0 || newR>=R) continue;
            if(newC<0 || newC>=C) continue;

            //탐색
            if(alpha[board[newR][newC] - 'A']==0) dfs(newR,newC);
        }
        alpha[board[r][c] - 'A'] --;
        count--;
//        if (alpha[board[r][c] - 'A']!=0){
//        }


    }

}