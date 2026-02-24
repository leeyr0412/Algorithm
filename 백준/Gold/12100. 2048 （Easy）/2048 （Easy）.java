import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        max = 0;
        int[][] board = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[r][c]);
            }
        }

        dfs(board, 0);

        System.out.println(max);
    }

    private static void dfs(int[][] board, int round) {
        if (round == 5) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] movedBoard = move(board);
            dfs(movedBoard, round + 1);
            board = rotate(board);
        }
    }

    private static int[][] rotate(int[][] board) {
        int[][] newBoard = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                newBoard[r][c] = board[c][N - 1 - r];
            }
        }
        return newBoard;
    }

    private static int[][] move(int[][] board) {
        int[][] newBoard = new int[N][N];
        // <(LEFT)방향으로 움직임
        for (int r = 0; r < N; r++) {
            int size = 0;
            int temp = 0;
            for (int c = 0; c < N; c++) {
                if (board[r][c] == 0) {
                    continue;
                }

                if (temp == 0) {
                    temp = board[r][c];
                } else if (temp == board[r][c]) {
                    newBoard[r][size++] = temp * 2;
                    max = Math.max(max, temp * 2);
                    temp = 0;
                } else {
                    newBoard[r][size++] = temp;
                    temp = board[r][c];
                }
            }
            if (temp != 0) {
                newBoard[r][size++] = temp;
            }
        }
        return newBoard;
    }
}