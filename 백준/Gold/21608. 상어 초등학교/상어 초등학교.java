import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 상어 초등학교 / 골드5 / 90분 / 3월 15일
 * https://www.acmicpc.net/problem/21608
 */     
public class Main {
    static int N;
    static int N2;
    static int[][] like;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //입력 받기
        N = Integer.parseInt(br.readLine());
        like = new int[N*N + 1][5];
        for (int i = 1; i <= N*N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                like[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //자리 배치하기
        N2 = N * N;
        map = new int[N][N];
        map[1][1] = like[1][0];

        for (int i = 2; i <= N*N; i++) {
            findSeat(like[i], like[i][0]);
        }
        System.out.println(getScore());
    }

    private static int getScore() {
        int score = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for(int i =1; i <= N*N;i++){
                    if(like[i][0]==map[r][c]){
                        int temp =findFriend(r, c, like[i]);
                        if(temp == 1){
                            score++;
                        } else if (temp == 2) {
                            score+=10;
                        } else if (temp==3) {
                            score+=100;
                        } else if (temp==4) {
                            score+=1000;
                        }
                    }
                }
            }
        }
        return score;
    }

    //한 사람의 자리 찾기
    private static void findSeat(int[] myFriend, int me) {
        int friend = 0;
        List<int[]> seat = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 0) {
                    int tempFreind = findFriend(r, c, myFriend);
                    if (tempFreind < friend) {
                        continue;
                    } else if (tempFreind > friend) {
                        seat = new ArrayList<>();
                        friend = tempFreind;
                        seat.add(new int[]{r, c});
                    } else {
                        seat.add(new int[]{r, c});
                    }
                }
            }
        }
        if (seat.size() == 1) { //1번 조건 만족
            map[seat.get(0)[0]][seat.get(0)[1]] = me;
        } else {  //2번, 3번 조건
            int empty = 0;
            int[] fitSeat = {N, N};
            for (int[] loc : seat) {
                int tempE = getEmpty(loc[0], loc[1]); //인접한 칸 수 구하기
                if (empty < tempE) {
                    fitSeat = loc;
                    empty = tempE;
                } else if (empty == tempE) {
                    if (fitSeat[0] > loc[0]) {
                        fitSeat = loc;
                    } else if (fitSeat[0] == loc[0]) {
                        if (fitSeat[1] > loc[1])
                            fitSeat = loc;
                    }
                }
            }
            map[fitSeat[0]][fitSeat[1]] = me;
        }
    }

    //자리에서 인접한 친구수 찾기
    private static int findFriend(int r, int c, int[] myFriend) {
        int[][] dr = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int friend = 0;
        for (int[] d : dr) {
            int newR = r + d[0];
            int newC = c + d[1];
            if (newR >= 0 && newR < N && newC >= 0 && newC < N) {
                for (int i = 1; i <= 4; i++) {
                    if (map[newR][newC] == myFriend[i]) {
                        friend++;
                    }
                }
            }
        }
        return friend;
    }

    //자리에서 인접한 빈자리 수 찾기
    private static int getEmpty(int r, int c) {
        int[][] dr = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int empty = 0;
        for (int[] d : dr) {
            int newR = r + d[0];
            int newC = c + d[1];
            if (newR >= 0 && newR < N && newC >= 0 && newC < N) {
                if (map[newR][newC] == 0) {
                    empty++;
                }
            }
        }
        return empty;
    }
}