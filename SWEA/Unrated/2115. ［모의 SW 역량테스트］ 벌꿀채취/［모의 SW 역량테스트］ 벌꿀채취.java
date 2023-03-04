import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int M;
    static int C;
    static int[][] arr;
    static int[] max;
    static ArrayList<ArrayList<Integer>> combiList = new ArrayList<>();
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            max = new int[N];
            
            combiList.clear();      //조합을 저장해두는 배열 초기화
            numbers = new int[M];
            for(int i = 1; i <= M; i++){
                combi(0,0,i , M);
            }

            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    arr[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            for(int y = 0; y<N;y++){
                for(int x = 0; x <= N-M;x++){
                    int temp;
                    for(ArrayList<Integer> c : combiList){
                        temp = 0;
                        int sum = 0;
                        for(int i : c){
                            sum += arr[y][x+i];
                        }
                        if(sum<=C){
                            for(int i : c){
                                temp += Math.pow(arr[y][x+i],2);
                            }
                        }
                        if(temp > max[y]){
                            max[y] = temp;
                        }
                    }
                }
            }
            Arrays.sort(max);
            System.out.println("#"+tc+" "+(max[N-1]+max[N-2]));
        }
    }

    static void combi(int cnt, int start, int M, int len) {
        if(cnt == M) {	//M만큼 골라지면 출력
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                temp.add(numbers[i]);
            }
            combiList.add(temp);
            return;
        }
        for(int i = start; i < len; i++) {	//인덱스 번호부터 대입
            numbers[cnt] = i;
            combi(cnt+1, i+1, M, len);
        }
    }
}
