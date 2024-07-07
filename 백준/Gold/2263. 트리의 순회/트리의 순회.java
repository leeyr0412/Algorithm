import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> preorder;
    static int[] inorder;
    static int[] postorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        preorder = new ArrayList<>();
        inorder = new int[N];
        postorder = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        divide(0, N, 0, N);

        for (Integer integer : preorder) {
            System.out.print(integer + " ");
        }
    }

    private static void divide(int inLeft, int inRight, int postLeft, int postRight) {
        if (inRight - inLeft != postRight - postLeft) {
            return;
        }
        if (inLeft < inRight && postLeft < postRight) {
            for (int i = 0; i < inRight - inLeft; i++) {
                if (inorder[inLeft + i] == postorder[postRight - 1]) {
                    preorder.add(postorder[postRight - 1]);
                    divide(inLeft, inLeft + i, postLeft, postLeft + i);
                    divide(inLeft + i + 1, inRight, postLeft + i, postRight - 1);
                }
            }
        }
    }
}