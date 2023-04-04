import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 찾기 / 플래티넘5 / 걸린시간 / 4월 4일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;//= new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();

        int tLength = text.length;
        int pLength = pattern.length;

        int[] pi = new int[pLength];
        //부분일치 테이블 만들기
        for (int i = 1, j = 0; i < pLength; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            } else {
                pi[i] = 0;
            }
        }

        int cnt = 0;
        List<Integer> list = new LinkedList<>();
        for (int i = 0, j = 0; i < tLength; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j - 1];
            }

            if(text[i]==pattern[j]){
                j++;
                if(j==pLength){
                    cnt++;
                    list.add(i-j+2);
                    j=pi[j-1];
                }
            }
        }
        System.out.println(cnt);
        for (Integer i : list) {
            System.out.print(i+" ");
        }

    }
}