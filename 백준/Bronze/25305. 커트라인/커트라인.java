import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Integer> pq = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < num; i++){
            pq.add(Integer.parseInt(st.nextToken()));
        }
        pq.sort(Comparator.reverseOrder());
        System.out.println(pq.get(k-1));

    }
}