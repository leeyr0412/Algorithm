import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 야구 / 골드4 / 2시간 / 3월 7일
 * https://www.acmicpc.net/problem/17281
 */
public class Main {
    static int[] people = {2, 3, 4, 5, 6, 7, 8, 9};
    static int[][] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int answer = 0;
        int inning = Integer.parseInt(br.readLine());
        score = new int[inning][9];
        for (int i = 0; i < inning; i++) {
            st = new StringTokenizer(br.readLine());
            for (int p = 0; p < 9; p++) {
                score[i][p] = Integer.parseInt(st.nextToken());
            }
        }   //이닝 별 타자가 치는 볼 기록
        
        do {    //1번 선수를 4번타자로 만들기
            int[] turn = new int[9];
            for (int i = 0; i < 3; i++) {
                turn[i] = people[i];
            }
            turn[3] = 1;
            for (int i = 4; i < 9; i++) {
                turn[i] = people[i - 1];
            }
            answer = Math.max(game(inning, turn), answer);
        } while (nextPerm(people));
        System.out.println(answer);
    }

    private static int game(int inning, int[] turn) {
        int curr = 0;       //현재 선수
        int currInning = 0; //현재 이닝
        int out;            //아웃 카운트
        int answer = 0;     //점수
        while (currInning < inning) {
            int[] base = new int[3];
            out = 0;
            while (out < 3) {
                int now = score[currInning][turn[curr] - 1];    //현재 선수가 친 점수
                if (now == 0) { //아웃
                    out++;
                } else if (now == 4) {  //홈런. 베이스의 선수들과 타자 점수로 나고, 베이스 초기화
                    for (int i = 0; i < 3; i++) {
                        answer += base[i];
                    }
                    answer++;
                    base = new int[3];
                } else {    //1,2,3 루타
                    answer += base[2];  //타자 넣기  
                    base[2] = base[1];
                    base[1] = base[0];
                    base[0] = 1;
                    for (int i = 1; i < now; i++) { //진루
                        answer += base[2];
                        base[2] = base[1];
                        base[1] = base[0];
                        base[0] = 0;
                    }
                }
                curr = (curr + 1) % 9;  //다음타자
            }
            currInning++;   //다음 이닝
        }
        return answer;
    }

    //선수 배치 정하기 위한 순열 구하기
    private static boolean nextPerm(int[] people) {
        int i = 7;
        while (i > 0 && people[i - 1] >= people[i]) i--;
        if (i == 0) return false;

        int j = 7;
        while (people[i - 1] >= people[j]) j--;

        swap(people, i - 1, j);

        int k = 7;
        while (i < k) {
            swap(people, i++, k--);
        }
        return true;
    }

    private static void swap(int[] people, int i, int j) {
        int temp = people[i];
        people[i] = people[j];
        people[j] = temp;
    }
}