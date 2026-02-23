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

        fun(board, 0);

        System.out.println(max);
    }

    private static void fun(int[][] board, int round) {
        if (round == 5) {
            return;
        }
        //상
        int[][] newBoard = boardCopy(board);
        move(newBoard, 0, round);
        //하
        newBoard = boardCopy(board);
        move(newBoard, 1, round);
        //좌
        newBoard = boardCopy(board);
        move(newBoard, 2, round);
        //우
        newBoard = boardCopy(board);
        move(newBoard, 3, round);
    }

    private static void move(int[][] board, int dir, int round) {
        Deque<Integer> que = new ArrayDeque<>();
        if (dir == 0) {// 상
            for (int c = 0; c < N; c++) {
                insertQue(board, que, c);
                if (que.size() == 0)
                    continue;
                // col 초기화
                for (int r = 0; r < N; r++) {
                    board[r][c] = 0;
                }
                if (que.size() == 1) {
                    board[0][c] = que.poll();
                    continue;
                }
                //2개 이상
                int temp = que.poll();
                int index = 0;
                while (!que.isEmpty()) {
                    Integer num = que.poll();
                    if (temp == 0) {
                        temp = num;
                    } else if (temp == num) {
                        max = Math.max(max, temp + num);
                        board[index++][c] = temp + num;
                        temp = 0;
                    } else {
                        board[index++][c] = temp;
                        temp = num;
                    }
                    if (que.size() == 0 && temp != 0) {
                        board[index][c] = temp;
                    }
                }
            }
        } else if (dir == 1) {  //하
            for (int c = 0; c < N; c++) {
                insertQue(board, que, c);
                if (que.size() == 0)
                    continue;
                // col 초기화
                for (int r = 0; r < N; r++) {
                    board[r][c] = 0;
                }
                if (que.size() == 1) {
                    board[N - 1][c] = que.pollLast();
                    continue;
                }
                //2개 이상
                int temp = que.pollLast();
                int index = N - 1;
                while (!que.isEmpty()) {
                    Integer num = que.pollLast();
                    if (temp == 0) {
                        temp = num;
                    } else if (temp == num) {
                        max = Math.max(max, temp + num);
                        board[index--][c] = temp + num;
                        temp = 0;
                    } else {
                        board[index--][c] = temp;
                        temp = num;
                    }
                    if (que.size() == 0 && temp != 0) {
                        board[index][c] = temp;
                    }
                }
            }
        } else if (dir == 2) {  //좌
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] != 0) {
                        que.add(board[r][c]);
                    }
                }
                if (que.size() == 0)
                    continue;
                // row 초기화
                for (int c = 0; c < N; c++) {
                    board[r][c] = 0;
                }
                if (que.size() == 1) {
                    board[r][0] = que.poll();
                    continue;
                }
                //2개 이상
                int temp = que.pollFirst();
                int index = 0;
                while (!que.isEmpty()) {
                    Integer num = que.poll();
                    if (temp == 0) {
                        temp = num;
                    } else if (temp == num) {
                        max = Math.max(max, temp + num);
                        board[r][index++] = temp + num;
                        temp = 0;
                    } else {
                        board[r][index++] = temp;
                        temp = num;
                    }
                    if (que.size() == 0 && temp != 0) {
                        board[r][index] = temp;
                    }
                }
            }
        } else {  //우
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] != 0) {
                        que.add(board[r][c]);
                    }
                }
                if (que.size() == 0)
                    continue;
                // col 초기화
                for (int c = 0; c < N; c++) {
                    board[r][c] = 0;
                }
                if (que.size() == 1) {
                    board[r][N - 1] = que.poll();
                    continue;
                }
                //2개 이상
                int temp = que.pollLast();
                int index = N - 1;
                while (!que.isEmpty()) {
                    Integer num = que.pollLast();
                    if (temp == 0) {
                        temp = num;
                    } else if (temp == num) {
                        max = Math.max(max, temp + num);
                        board[r][index--] = temp + num;
                        temp = 0;
                    } else {
                        board[r][index--] = temp;
                        temp = num;
                    }
                    if (que.size() == 0 && temp != 0) {
                        board[r][index] = temp;
                    }
                }
            }
        }

        fun(board, round + 1);
    }

    private static void insertQue(int[][] board, Deque<Integer> que, int c) {
        for (int r = 0; r < N; r++) {
            if (board[r][c] != 0) {
                que.add(board[r][c]);
            }
        }
    }

    private static int[][] boardCopy(int[][] board) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            newBoard[i] = board[i].clone();
        }
        return newBoard;
    }
}