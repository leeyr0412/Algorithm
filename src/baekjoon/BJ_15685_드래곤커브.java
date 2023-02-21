package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 드래곤 커브 / 골드4 / 1시간
 * https://www.acmicpc.net/problem/15685
 */
public class BJ_15685_드래곤커브 {
    static int[][] dr = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static int[][] map = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int result = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            Stack<Integer> stack = new Stack<>();
            stack.push(d);
            Stack<Integer> newStack = new Stack<>();
            newStack.push(d);
            map[y][x] = 1;
            y = y + dr[d][0];
            x = x + dr[d][1];
            map[y][x] = 1;

            for (int g = 1; g <= G; g++) {
                while (!stack.empty()) {
                    int nowD = (stack.pop() + 1) % 4;
                    y = y + dr[nowD][0];
                    x = x + dr[nowD][1];
                    map[y][x] = 1;
                    newStack.push(nowD);
                }
                stack = (Stack<Integer>) newStack.clone();

            }
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(map[i][j]==1){
                    if(map[i][j+1] == 1 && map[i+1][j]==1 && map[i+1][j+1]==1) result++;
                }
            }
        }
        System.out.println(result);
    }
}
