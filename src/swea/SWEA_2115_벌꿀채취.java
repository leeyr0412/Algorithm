package yeri.algorithm0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취 {
    static int N;
    static int M;
    static int C;
    static int[][] arr;
    static int[] max;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            max = new int[N];

            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    arr[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            for(int y = 0; y<N;y++){
                for(int x = 0; x <= N-C;x++){

                }
            }
        }
    }

    static int[] numbers;

    void combi(int cnt, int start) {
        if(cnt == M) {	//M만큼 골라지면 출력
            for(int i = 0; i < M; i++) {
                System.out.print(numbers[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i <= N; i++) {	//인덱스 번호부터 대입
            numbers[cnt] = i;
            combi(cnt+1, i+1);
        }
    }
}
