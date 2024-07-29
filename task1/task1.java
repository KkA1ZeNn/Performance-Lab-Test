import java.util.Arrays;
import java.util.Scanner;

public class task1 {

    public static void main(String[] args) {
        var console = new Scanner(System.in);
        System.out.print("n = ");
        int n = console.nextInt();
        System.out.print("m = ");
        int m = console.nextInt();

        //counter - отвечает за сдвиг на m символов, pointer - за указатель на текущий элемент массива
        int counter = 0;
        int pointer = 0;
        int[] arr = new int[n];
        String answer = "";

        //заполняем массив
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }

        while (true) {
            if (counter % m == 0) {
                answer += arr[pointer];
                --pointer;
            }
            ++counter;
            ++pointer;

            if (pointer == arr.length)
                pointer = 0;

            if (pointer == 0 && counter % m == 0)
                break;
        }

        System.out.println("answer = " + answer);
    }
}