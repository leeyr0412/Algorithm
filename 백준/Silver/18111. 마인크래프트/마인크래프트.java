import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int R;
    static int C;
    static int block;
    static int time = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        block = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        boolean[] checked = new boolean[257];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int ansTall = 0;
        for(int i = 0; i <= 256;i++){
            int temp = search(i);
            if(temp < 0) {
                continue;
            }
            time = temp;
//            if (temp == time) {
                ansTall = i;
//            }
        }
        System.out.println(time + " "+ansTall);
    }

    private static int search(int i) {
        int tempTime=0;
        int tempBlock = block;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(map[r][c] < i){
                    tempBlock = tempBlock - (i-map[r][c]);
                    tempTime+=i-map[r][c];
                }else{
                    tempBlock+=map[r][c]-i;
                    tempTime+=(map[r][c]-i)*2;
                }
                if(tempTime > time)
                    return -1;
            }
        }
        if(tempBlock<0){
            return tempBlock;
        }
        return tempTime;
    }
}