package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나무 자르기 / 실버2 / 2시간
 */
public class BJ_2805_나무자르기 {

    static int M;
    static int N;
    static int [] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            if(max < tree[i])
                max = tree[i];
        }
        System.out.println(binary_search(0, max, 0));
    }

    private static int binary_search(int min, int max, int result) {
        if(min >= max){
            return result;
        }
        int mid = (min + max) / 2;
        long treeLen = getTree(mid);
        if (treeLen == M) {
            return mid;
        }
        if (treeLen >= M) {
            return binary_search(mid+1 , max, Math.max(mid, result));
        } else {
            return binary_search(min, mid , result);
        }
    }

    private static long getTree(int mid) {
        long sum = 0;
        for (int h : tree) {
            if(h-mid>0)
                sum += (h - mid);
        }
        return sum;
    }
}

