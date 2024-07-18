import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();
        if (!str1.contains("Fizz") && !str1.contains("Buzz")) {
            int answerNum = Integer.parseInt(str1) + 3;
            printAnswer(answerNum);
        } else if (!str2.contains("Fizz") && !str2.contains("Buzz")) {
            int answerNum = Integer.parseInt(str2) + 2;
            printAnswer(answerNum);
        } else {
            int answerNum = Integer.parseInt(str3) + 1;
            printAnswer(answerNum);
        }

    }

    private static void printAnswer(int answerNum) {
        if (answerNum % 15 == 0) {
            System.out.println("FizzBuzz");
        } else if (answerNum % 3 == 0) {
            System.out.println("Fizz");
        } else if (answerNum % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(answerNum);
        }
    }
}