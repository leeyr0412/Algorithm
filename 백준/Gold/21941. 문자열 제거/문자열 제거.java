import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문자열제거 / 골드4 / 시간 / 4월27일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        Map<String, Integer> score = new HashMap<>();
        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int point = Integer.parseInt(st.nextToken());
            if(point<=s.length())
                continue;
            score.put(s,point);
        }
        int [] dp = new int[str.length()+1];
        dp[0] = 0;
        for(int i = 1; i <= str.length(); i++){
            dp[i] = dp[i-1]+1;
            for (String s : score.keySet()) {
                if (i+1>s.length()){
                    boolean check = true;
                    for(int j = 0; j < s.length();j++){
                        if(str.charAt(i-s.length()+j)!=s.charAt(j)){
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        dp[i] = Math.max(dp[i],dp[i-s.length()]+score.get(s));
                    }
                }
            }
        }
        System.out.println(dp[str.length()]);
    }
}