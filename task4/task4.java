import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task4 {
    public static int minMoves(List<Integer> nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / nums.size();
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - target);
        }
        return moves;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Пожалуйста, укажите имя файла в качестве аргумента командной строки.");
            return;
        }

        String filename = args[0];
        List<Integer> nums = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                nums.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        int result = minMoves(nums);
        System.out.println(result);
    }
}
