import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
   static int K;			//회전 수
    static int N;
    static int M;
    static int result = Integer.MAX_VALUE;
    static int [][] turn_cal;
    static  int[][] arr;
    static int[][] temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        turn_cal = new int [K][3];
        arr = new int[N][M];
        temp = new int[N][M];
        numbers = new int[K];
        isSelected = new boolean[K];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turn_cal[i][0] =Integer.parseInt(st.nextToken());
            turn_cal[i][1] =Integer.parseInt(st.nextToken());
            turn_cal[i][2] =Integer.parseInt(st.nextToken());





        }
        perm(0);
        System.out.println(result);
    }

    static int getArrSum(){
        int min =Integer.MAX_VALUE;
        for (int y = 0; y < N; y++) {
            int t = 0;
            for (int x = 0; x < M; x++) {
                t += temp[y][x];
            }
            min = Math.min(t, min);
        }
        return Math.min(result, min);
    }

    static void turn(int k){    //k:연산 번호
        //회전
        for (int g = 1; g <= turn_cal[k][2]; g++) {
            Deque<Integer> queue = new ArrayDeque<>();

            for (int x = turn_cal[k][1] - g - 1; x < turn_cal[k][1] + g; x++) {
                queue.add(temp[turn_cal[k][0] - g - 1][x]);
            }
            for (int y = turn_cal[k][0] - g; y < turn_cal[k][0] + g; y++) {
                queue.add(temp[y][turn_cal[k][1] + g - 1]);
            }
            for (int x = turn_cal[k][1] + g - 2; x >= turn_cal[k][1]-g-1; x--) {
                queue.add(temp[turn_cal[k][0] + g - 1][x]);
            }
            for (int y = turn_cal[k][0] + g - 2; y > turn_cal[k][0]-g-1; y--) {
                queue.add(temp[y][turn_cal[k][1] - g - 1]);
            }
            queue.addFirst(queue.pollLast());
            for (int x = turn_cal[k][1] - g - 1; x < turn_cal[k][1] + g; x++) {
                temp[turn_cal[k][0] - g - 1][x] = queue.pollFirst();
            }
            for (int y = turn_cal[k][0] - g; y < turn_cal[k][0] + g; y++) {
                temp[y][turn_cal[k][1] + g - 1] = queue.pollFirst();
            }
            for (int x = turn_cal[k][1] + g - 2;x >= turn_cal[k][1]-g-1; x--) {
                temp[turn_cal[k][0] + g - 1][x] = queue.pollFirst();
            }
            for (int y = turn_cal[k][0] + g - 2;y > turn_cal[k][0]-g-1; y--) {
                temp[y][turn_cal[k][1] - g - 1] = queue.pollFirst();
            }
        }//회전끝
    }

    static int[] numbers;	//배열 번호 저장
    static boolean[] isSelected;	//순열에 들어간 원소인지 체크

    static void perm(int cnt) {
        if (cnt == K) {	//cnt가 순열 크기이면 종료하고 출력
            for(int i = 0; i < arr.length; i++) {
                System.arraycopy(arr[i], 0, temp[i], 0, arr[i].length);
            }
            for (int i = 0; i < K; i++) {
                turn(numbers[i]-1);
            }
            result = Math.min(result,getArrSum());
            return;
        } else {
            for (int i = 1; i <= K; i++) {
                if (isSelected[i - 1] == true) {	//배열에 이미 들어가있으면 종료
                    continue;
                }
                numbers[cnt] = i;
                isSelected[i - 1] = true;		//배열에 인덱스가 들어갔음을 표시
                perm(cnt + 1);					//다음 탐색
                isSelected[i - 1] = false;		//다시 false로 표기하여 다음 탐색을 할 수 있게함
            }
        }
    }
}