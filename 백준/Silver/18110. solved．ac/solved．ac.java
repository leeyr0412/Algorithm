import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Collections.sort;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.println(0);
            return;
        }

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        sort(arr);

        int cut = (int) Math.round(n * 0.15);
        int sum = 0;
        for (int i = cut; i < n - cut; i++) {
            sum += arr.get(i);
        }

        int answer = (int) Math.round((sum*1.0) / (n - (2 * cut)));
        System.out.println(answer);
    }
}