import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> sName = new HashSet<>();
        Set<String> hName = new HashSet<>();
        for(int i = 1; i <= N; i++){
            String name = br.readLine();
            sName.add(name);
        }
        for(int i = 0; i < M;i++){
            String name = br.readLine();
            hName.add(name);
        }
        sName.retainAll(hName);
        List<String> nameList = new ArrayList<>(sName);
        Collections.sort(nameList);
        System.out.println(nameList.size());
        for (String name : nameList) {
            System.out.println(name);
        }
    }
}