import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 벽돌 깨기 / 1시간 반
 */
public class Solution {
    static int H;
    static int W;
    static int answer = Integer.MAX_VALUE;
    static int[][] board;
    static int[][] originalBoard;
    static int[][] dr = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer = Integer.MAX_VALUE; //초기화 및 입력받기
            st = new StringTokenizer(br.readLine());
            int balls = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            board = new int[H][W];
            originalBoard = new int[H][W];
            for (int h = 0; h < H; h++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++) {
                    board[h][w] = Integer.parseInt(st.nextToken());
                    originalBoard[h][w] = board[h][w];
                }
            }
            perm = new int[balls];
            //조합구하기
            permutation(W, balls, 0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static int[] perm;

    private static void permutation(int w, int n, int cnt) {
        if (cnt == n) {//순열 구해짐
            gameGo();   //게임하기
            answer = Math.min(answer, count()); //남은 블럭 수 세서 최솟값 구하기
            copyBoard();    //다시 보드 복사
            return;
        }
        for (int i = 0; i < w; i++) {
            perm[cnt] = i;
            permutation(w, n, cnt + 1);
        }
    }

    private static void copyBoard() {
        for (int row = 0; row < H; row++) {
            board[row] = originalBoard[row].clone();
        }
    }

    private static int count() {        //블럭 수 세기
        int count = 0;
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (board[r][c] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void gameGo() {      //구해진 순열으로 맨 위 블럭 찾기
        for (int w : perm) {
            for (int h = 0; h < H; h++) {
                if (board[h][w] != 0) {
                    shoot(h, w);    //맨 위 블럭부터 없앨수있는 놈 없애기
                    fall();         //아래로 떨어짐
                    break;
                }
            }
        }
    }

    private static void fall() {    //떨어짐
        Deque<Integer> que = new ArrayDeque<>();
        for (int c = 0; c < W; c++) {       //아래부터 큐에 다 넣고
            for (int r = H - 1; r >= 0; r--) {
                if (board[r][c] > 0) {
                    que.offer(board[r][c]);
                }
            }
            for (int r = H - 1; r >= 0; r--) {  //다시 아래부터 채우기
                if (que.size() > 0) {
                    board[r][c] = que.poll();
                } else {
                    board[r][c] = 0;
                }
            }
        }
    }

    private static void shoot(int r, int c) {   //쏘기 bfs
        Deque<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{r, c, board[r][c]});
        board[r][c] = 0;

        while (!que.isEmpty()) {
            int[] pos = que.poll();
            for (int power = 1; power < pos[2]; power++) {
                for (int[] d : dr) {
                    int newR = pos[0] + d[0] * power;
                    int newC = pos[1] + d[1] * power;
                    if (newR >= 0 && newR < H && newC >= 0 && newC < W) {
                        if (board[newR][newC] > 0) {
                            que.offer(new int[]{newR, newC, board[newR][newC]});
                            board[newR][newC] = 0;
                        }
                    }
                }
            }
        }
    }
}