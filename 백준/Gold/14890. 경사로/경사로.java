import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            //int[] road = new int[N];
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                //road[c] = map[r][c];
            }

        }
        int[][] roads = new int[2 * N][N];
        int i = 0;
        while (i < N) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    roads[i][c] = map[r][c];
                }
                i++;
            }
        }
        while (i < 2 * N) {
            for (int c = 0; c < N; c++) {
                for (int r = 0; r < N; r++) {
                    roads[i][r] = map[r][c];
                }
                i++;
            }
        }

        int answer = 0;
        for (int[] road : roads) {
            if (canCross(road, L)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean canCross(int[] road, int l) {
        int height = road[0];    // 기준 높이
        int nextIndex = 1;
        boolean[] check = new boolean[road.length];
        while (nextIndex < road.length) {
            if (height == road[nextIndex]) {// 기준 높이와 같음
                nextIndex++;
                continue;
            } else {
                if (Math.abs(height - road[nextIndex]) > 1) {
                    // 높이 차이가 2 이상
                    return false;
                } else if (height > road[nextIndex]) { //내리막
                    if (nextIndex + l - 1 >= road.length) { //경사로 불가
                        return false;
                    }
                    for (int checkIndex = 0; checkIndex < l; checkIndex++) {
                        if (height - 1 != road[nextIndex + checkIndex] || check[nextIndex + checkIndex]) {
                            return false;
                        }
                        //경사로 배치
                        check[nextIndex + checkIndex] = true;
                    }
                    height = road[nextIndex];
                    nextIndex = nextIndex + l - 1;
                } else {//오르막
                    if (nextIndex - l < 0) { //경사로 불가
                        return false;
                    }
                    for (int checkIndex = 1; checkIndex <= l; checkIndex++) {
                        if (height != road[nextIndex - checkIndex] || check[nextIndex - checkIndex]) {
                            return false;
                        }
                        //경사로 배치
                        check[nextIndex - checkIndex] = true;
                    }
                    height = road[nextIndex];
                    nextIndex++;
                }
            }
        }
        return true;
    }
}