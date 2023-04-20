import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 큐빙 / 플래5 / 시간 / 4월 20일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            char[][][] cube = {
                    {//0위
                            {'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}
                    },
                    {//1앞
                            {'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}
                    },
                    {//2아
                            {'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}
                    },
                    {//3뒤
                            {'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}
                    },
                    {//4왼
                            {'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}
                    },
                    {//5오
                            {'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}
                    }
            };
            int turnNum = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int t = 0; t < turnNum; t++) {
                String command = st.nextToken();
                switch (command.charAt(0)) {
                    case 'U':
                        if (command.charAt(1) == '-') {
                            turn(cube, 0);
                            turn(cube, 0);
                        }
                        turn(cube, 0);
                        break;
                    case 'D':
                        if (command.charAt(1) == '-') {
                            turn(cube, 2);
                            turn(cube, 2);
                        }
                        turn(cube, 2);
                        break;
                    case 'F':
                        if (command.charAt(1) == '-') {
                            turn(cube, 1);
                            turn(cube, 1);
                        }
                        turn(cube, 1);
                        break;
                    case 'B':
                        if (command.charAt(1) == '-') {
                            turn(cube, 3);
                            turn(cube, 3);
                        }
                        turn(cube, 3);
                        break;
                    case 'L':
                        if (command.charAt(1) == '-') {
                            turn(cube, 4);
                            turn(cube, 4);
                        }
                        turn(cube, 4);
                        break;
                    case 'R':
                        if (command.charAt(1) == '-') {
                            turn(cube, 5);
                            turn(cube, 5);
                        }
                        turn(cube, 5);
                        break;
                }
//                for (int r = 0; r < 3; r++) {
//                    for (int c = 0; c < 3; c++) {
//                        System.out.print(cube[0][r][c]);
//                    }
//                    System.out.println();
//                }System.out.println();
//                for (int r = 0; r < 3; r++) {
//                    for (int c = 0; c < 3; c++) {
//                        System.out.print(cube[1][r][c]);
//                    }
//                    System.out.println();
//                }System.out.println();
//                for (int r = 0; r < 3; r++) {
//                    for (int c = 0; c < 3; c++) {
//                        System.out.print(cube[2][r][c]);
//                    }
//                    System.out.println();
//                }System.out.println();
//                for (int r = 0; r < 3; r++) {
//                    for (int c = 0; c < 3; c++) {
//                        System.out.print(cube[3][r][c]);
//                    }
//                    System.out.println();
//                }System.out.println();
//                for (int r = 0; r < 3; r++) {
//                    for (int c = 0; c < 3; c++) {
//                        System.out.print(cube[4][r][c]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//                for (int r = 0; r < 3; r++) {
//                    for (int c = 0; c < 3; c++) {
//                        System.out.print(cube[5][r][c]);
//                    }
//                    System.out.println();
//                }System.out.println();System.out.println();System.out.println();
            }
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    System.out.print(cube[0][r][c]);
                }
                System.out.println();
            }
        }
    }

    private static void turn(char[][][] cube, int i) {
        Deque<Character> que = new ArrayDeque<>();
        que.add(cube[i][2][0]);
        que.add(cube[i][1][0]);
        que.add(cube[i][0][0]);
        que.add(cube[i][0][1]);
        que.add(cube[i][0][2]);
        que.add(cube[i][1][2]);
        que.add(cube[i][2][2]);
        que.add(cube[i][2][1]);
        cube[i][0][0] = que.poll();
        cube[i][0][1] = que.poll();
        cube[i][0][2] = que.poll();
        cube[i][1][2] = que.poll();
        cube[i][2][2] = que.poll();
        cube[i][2][1] = que.poll();
        cube[i][2][0] = que.poll();
        cube[i][1][0] = que.poll();

        if (i == 0) {           //위 회전
            char[] temp = {cube[1][0][0], cube[1][0][1], cube[1][0][2]};
            for (int c = 0; c < 3; c++) {
                cube[1][0][c] = cube[5][0][c];
                cube[5][0][c] = cube[3][0][c];
                cube[3][0][c] = cube[4][0][c];
                cube[4][0][c] = temp[c];
            }
        } else if (i == 1) {    //앞 회전
            char[] temp = {cube[0][2][2], cube[0][2][1], cube[0][2][0]};
            for (int c = 0; c < 3; c++) {
                cube[0][2][2-c] = cube[4][c][2];
                cube[4][c][2] = cube[2][0][c];
                cube[2][0][c] = cube[5][2 - c][0];
                cube[5][2 - c][0] = temp[c];
            }
        } else if (i == 2) {    //아래 회전
            char[] temp = {cube[5][2][2], cube[5][2][1], cube[5][2][0]};
            for (int c = 0; c < 3; c++) {
                cube[5][2][2 - c] = cube[1][2][2 - c];
                cube[1][2][2 - c] = cube[4][2][2 - c];
                cube[4][2][2 - c] = cube[3][2][2 - c];
                cube[3][2][2 - c] = temp[c];
            }
        } else if (i == 3) {    //뒤 회전
            char[] temp = {cube[5][0][2], cube[5][1][2], cube[5][2][2]};
            for (int c = 0; c < 3; c++) {
                cube[5][c][2] = cube[2][2][2-c];
                cube[2][2][2-c] = cube[4][2-c][0];
                cube[4][2-c][0] = cube[0][0][c];
                cube[0][0][c] = temp[c];
            }
        } else if (i == 4) {    //왼쪽 회전
            char[] temp = {cube[3][0][2], cube[3][1][2], cube[3][2][2]};
            for (int c = 0; c < 3; c++) {
                cube[3][c][2] = cube[2][2 - c][0];
                cube[2][2 - c][0] = cube[1][2 - c][0];
                cube[1][2 - c][0] = cube[0][2 - c][0];
                cube[0][2 - c][0] = temp[c];
            }
        } else {
            char[] temp = {cube[1][0][2], cube[1][1][2], cube[1][2][2]};
            for (int c = 0; c < 3; c++) {
                cube[1][c][2] = cube[2][c][2];
                cube[2][c][2] = cube[3][2 - c][0];
                cube[3][2 - c][0] = cube[0][c][2];
                cube[0][c][2] = temp[c];
            }
        }
    }
}
/*
1
1
F+ R+ U+ B+ L+ D+
 */