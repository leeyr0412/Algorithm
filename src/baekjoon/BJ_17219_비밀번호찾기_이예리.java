package ws.ws0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 비밀번호찾기 / 실버4 / 10분 / 3월 9일
 * https://www.acmicpc.net/problem/17219
 */
public class BJ_17219_비밀번호찾기_이예리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> map = new HashMap<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine()," ");
            String site = st.nextToken();
            String pw = st.nextToken();
            map.put(site, pw);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <M; i++){
            String key = br.readLine().trim();

            sb.append(map.get(key)).append("\n");
        }
        System.out.println(sb);
    }
}
