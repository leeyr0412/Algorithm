import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int nowR = Integer.parseInt(st.nextToken());
        int nowC = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dirs = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int[] rowList = {0, 0, 0, 0};   //0번이 위 오른쪽으로
        int[] colList = {0, 0, 0, 0};   //0번 위, 아래로
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            int c = Integer.parseInt(st.nextToken());
            int nextR = nowR + dirs[c][0];
            int nextC = nowC + dirs[c][1];
            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                continue;
            }

            // 회전
            if (c == 1) {// 동
                int temp = rowList[3];
                rowList[3] = rowList[2];
                rowList[2] = rowList[1];
                rowList[1] = rowList[0];
                rowList[0] = temp;
                colList[0] = rowList[0];
                colList[2] = rowList[2];

            } else if (c == 2) {  //서
                int temp = rowList[0];
                rowList[0] = rowList[1];
                rowList[1] = rowList[2];
                rowList[2] = rowList[3];
                rowList[3] = temp;
                colList[0] = rowList[0];
                colList[2] = rowList[2];
            } else if (c == 3) {  //북
                int temp = colList[0];
                colList[0] = colList[1];
                colList[1] = colList[2];
                colList[2] = colList[3];
                colList[3] = temp;
                rowList[0] = colList[0];
                rowList[2] = colList[2];
            } else { //남
                int temp = colList[3];
                colList[3] = colList[2];
                colList[2] = colList[1];
                colList[1] = colList[0];
                colList[0] = temp;
                rowList[0] = colList[0];
                rowList[2] = colList[2];
            }
            System.out.println(rowList[0]);
            // 복사
            if (map[nextR][nextC] == 0) {
                map[nextR][nextC] = rowList[2];
            } else {
                rowList[2] = map[nextR][nextC];
                colList[2] = map[nextR][nextC];
                map[nextR][nextC] = 0;
            }
            nowR = nextR;
            nowC = nextC;
        }
    }
}