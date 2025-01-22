import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] nameList = new String[N+1];
        Map<String, Integer> numList = new HashMap();
        for(int i = 1; i <= N; i++){
            String name = br.readLine();
            nameList[i] = name;
            numList.put(name,i);
        }
        for(int i = 0; i < M;i++){
            String q = br.readLine();
            if(numList.containsKey(q)){
                System.out.println(numList.get(q));
            }else {
                System.out.println(nameList[Integer.parseInt(q)]);
            }
        }
    }
}