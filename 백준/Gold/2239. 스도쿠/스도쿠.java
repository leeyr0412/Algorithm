import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 스도쿠 / 골드4 / 걸린시간 / 3월 30일
 */
public class Main {
    static int[][] board = new int[10][10];
    static int[][] answer = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();


        for (int r = 1; r < 10; r++) {
            String line = br.readLine();
            for (int c = 1; c < 10; c++) {
                board[r][c] = line.charAt(c - 1) - '0';
            }
        }

        for (int r = 1; r < 10; r++) {
            for (int c = 1; c < 10; c++) {
                if (board[r][c] == 0) {
                    dfs(r, c, board);
                }
            }
        }


//        printB();//
    }

    private static void dfs(int r, int c, int[][] board) {
        boolean[] use = findNum(r, c, board);
//        System.out.println("use = " + Arrays.toString(use));


        for (int i = 1; i < 10; i++) {
            if (!use[i]) {
//                System.out.println("r = " + r + ", c = "+c);
                board[r][c] = i;
//                printB();
                int[] next = findEmpty(board);
                if (next == null) {
                    printB();
                    System.exit(0);
                }
                dfs(next[0], next[1], board);
                board[r][c] = 0;
            }
        }
    }

    private static int[] findEmpty(int[][] board) {
        for (int r = 1; r < 10; r++) {
            for (int c = 1; c < 10; c++) {
                if (board[r][c] == 0) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }

    private static boolean[] findNum(int r, int c, int[][] board) {
        boolean[] use = new boolean[10];
        //가로 검사
        for (int i = 1; i < 10; i++) {
            if (board[r][i] > 0) {
                use[board[r][i]] = true;
            }
        }
        //세로 검사
        for (int i = 1; i < 10; i++) {
            if (board[i][c] > 0) {
                use[board[i][c]] = true;
            }
        }
        //블록검사
        int br = (r - 1) / 3;
        int bc = (c - 1) / 3;
        for (int nr = br * 3 + 1; nr < br * 3 + 4; nr++) {
            for (int nc = bc * 3 + 1; nc < bc * 3 + 4; nc++) {
                use[board[nr][nc]] = true;
            }
        }
        return use;
    }


    //지우기
    private static void printB() {
        for (int r = 1; r < 10; r++) {
            for (int c = 1; c < 10; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
//        System.out.println("\n========================");
    }


}