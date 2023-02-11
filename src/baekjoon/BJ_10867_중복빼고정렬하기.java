package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 */
public class BJ_10867_중복빼고정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> temp = new HashSet<>();
        for(int i = 0; i < N; i++){
            temp.add(Integer.parseInt(st.nextToken()));
        }
        ArrayList<Integer> result = new ArrayList<>(temp);
        Collections.sort(result);
        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i) + " ");
        }
    }
}
