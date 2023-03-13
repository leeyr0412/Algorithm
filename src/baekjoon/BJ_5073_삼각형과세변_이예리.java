package ws.ws0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 삼각형과 세 변 / 난이도 / 걸린시간 / 날짜
 * https://www.acmicpc.net/problem/5073
 */
public class BJ_5073_삼각형과세변_이예리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            int[] arr = new int[1001];
            int max = 0;
            for (int i = 0; i < 3; i++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 0) {
                    return;
                }
                list.add(a);
                arr[a]++;
                max = Math.max(max, arr[a]);
            }
            Collections.sort(list);
            if (max == 3) {
                System.out.println("Equilateral ");
            } else if (max == 2) {
                if (list.get(2) > list.get(0) + list.get(1)) {
                    System.out.println("Invalid");
                } else {
                    System.out.println("Isosceles");
                }
            } else {
                if (list.get(2) < list.get(0) + list.get(1)) {
                    System.out.println("Scalene");
                } else {
                    System.out.println("Invalid");
                }
            }
        }
    }
}
