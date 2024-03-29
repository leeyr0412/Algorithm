import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

//        boolean[] cards = new boolean[N + 1];
//        boolean[] haveNext = new boolean[N + 1];
//        int[] arr = new int[N + 1];
//
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < M; i++) {
//            int pick = Integer.parseInt(st.nextToken());
//            cards[pick] = true;
////            arr[pick] = pick;
//        }
//
//        int nextIndex = N;
//        boolean next = false;
//
//        for (int i = N; i > 0; i--) {
//            if (cards[i]) {
////                nextIndex = i;
//                haveNext[i] = next;
//                arr[i] = nextIndex;
//                next = true;
//                nextIndex = i;
//                continue;
//            }
//            haveNext[i] = next;
//            arr[i] = nextIndex;
////            arr[i] = temp;
//        }

        boolean[] cards = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int pick = Integer.parseInt(st.nextToken());
            cards[pick] = true;
        }

        int[] arr = new int[N + 1];
        int temp = N;

        for (int i = N; i > 0; i--) {
            if (cards[i]) {
                arr[i] = temp;
                temp = i;
                continue;
            }
            arr[i] = temp;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int index = Integer.parseInt(st.nextToken());
            while (!cards[arr[index]]) {
                index = arr[index];
            }
            sb.append(arr[index]).append("\n");
            cards[arr[index]] = false;
            if (arr[index] != N) {
                arr[index] = arr[index] + 1;
            }
        }
        System.out.println(sb);
    }
}