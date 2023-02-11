package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2023_신기한소수 {
    static int N;
    static StringBuilder sb= new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        getPrime(0, 0);
        System.out.println(sb);
    }

    public static void getPrime(int num, int cnt) {
        if (cnt == N) {
            if (isPrime(num)) {
                sb.append(num).append("\n");
            }
            return;
        }
        for (int i = 0; i < 10; i += 1) {
            int next = num * 10 + i;
            if (isPrime(next)) {
                getPrime(next, cnt + 1);
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

}

