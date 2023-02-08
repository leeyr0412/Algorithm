package baekjoon;

import java.util.Scanner;

public class BJ_6588_골드바흐의추측 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] prime = new boolean[1000001];

        for (int i = 3; i <= Math.sqrt(1000000); i++){
            if(prime[i]){
                continue;
            }
            for(int j = i * i; j < prime.length; j += i){
                prime[j] = true;
            }
        }

        int n = sc.nextInt();
        do{
            int a = 0;
            for(int i = 3; i <= n/2; i+=2){
                if (!prime[i] && !prime[n-i]){
                    a = i;
                    break;
                }
            }

            if(a != 0){
                System.out.println(n + " = " + a + " + " + (n-a));
            }else{
                System.out.println("Goldbach's conjecture is wrong.");
            }

            n = sc.nextInt();

        }while (n!=0);


    }
}
