package ws.ws0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소힙 / 실버2 / 3분 / 3월 10일
 * https://www.acmicpc.net/problem/1927
 */
public class BJ_1927_최소힙_이예리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            int input = Integer.parseInt(br.readLine());
            if(input>0){
                q.add(input);
            }else{
                if(q.size()==0){
                    System.out.println(0);
                }else{
                    System.out.println(q.poll());
                }
            }
        }
    }
}
