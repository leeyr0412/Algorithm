package ws.ws0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임_이예리 {
    static int N=9;
    static int[] numbers;
    static boolean[] isSelected;
    static int[] G = new int [9];
    static ArrayList<Integer> cards;
//    static int N=9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int T = Integer.parseInt(br.readLine());

        int [] I = new int [9];
        numbers = new int[N];
        isSelected = new boolean[N];
        for(int tc = 1; tc <=T; tc++){
             cards = new ArrayList<>();
            for(int i = 1; i <=18; i++){
                cards.add(i);
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 9; i++){
                G[i] = Integer.parseInt(st.nextToken());
                cards.remove(G[i]-1);
            }
            int gScore = 0;
            int iScore = 0;
            for(int i = 0; i < 9; i++){
                if(g)
            }
        }
    }

    static void dfs(int cnt) {
        if(cnt == N) {
            for(int i = 0; i < N; i++) {
                game();
//                System.out.print(numbers[i] + " ");
            }
//            System.out.println();
            return;
        }else {
            for(int i = 1; i <= N; i++) {
                if(isSelected[i-1] == true) {
                    continue;
                }
                numbers[cnt] = i;
                isSelected[i-1] = true;
                dfs(cnt+1);
                isSelected[i-1] = false;
            }
        }
    }

    private static void game() {
        int gScore = 0;
        int iScore = 0;
        for(int i = 0; i < 9; i++){
            if(G[i] > cards.get(numbers[i]-1)){
                gScore+=G[i]+cards.get(numbers[i]-1);
            }
        }
    }
}
