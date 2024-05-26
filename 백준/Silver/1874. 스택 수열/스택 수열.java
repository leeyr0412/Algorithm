import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] stack = new int[n];
        char[] ans = new char[n * 2];     //결과 출력 위한 배열 (+ -)
        int top = -1;                   //스택 top 인덱스 -1: 스택 비어있음
        int curr = 0;                   //수열 1~n까지 현재 스택에 들어갔던 마지막 숫자임
        int anum = -1;                  //결과 배열 인덱스
        int gg;       //만들어야할 수열 숫자

        for (int i = 0; i < n; i++) {       //수열에 1~n까지 들어감
            gg = Integer.parseInt(br.readLine());
            if (curr < gg) {                      //스택에 없음 - 아직 들어간 적 없는 상태
                for (int j = curr + 1; j <= gg; j++) {//스택에 숫자 넣음
                    stack[++top] = j;
                    sb.append('+').append('\n');
                }
                curr = gg;
            }
            else if(stack[top]!=gg){
                System.out.println("NO");
                return;
            }
            //팝
            top--;
            sb.append('-').append('\n');
        }
        System.out.println(sb);
    }
}