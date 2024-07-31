import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    private static int circleX;
    private static int circleY;
    private static int circleRadius;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println( "Пожалуйста, укажите путь к файлу с информацией об окружности " +
                                "и путь к файлу с информацией о точках" +
                                "в качестве аргумента командной строки.");
            return;
        }

        String circlePath = args[0];
        String pointsPath = args[1];

        try {
            readCircleInfo(circlePath);
            readPointsInfo(pointsPath);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void readCircleInfo(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] coords = reader.readLine().split("\\s+");
            String radius = reader.readLine();
            setCircleX(coords[0]);
            setCircleY(coords[1]);
            setCircleRadius(radius);
        }
    }

    private static void readPointsInfo(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int xPoint;
            int yPoint;
            String line;
            while ((line = reader.readLine())!= null && !line.trim().isEmpty()) {
                String[] coords = line.split("\\s+");
                xPoint = Integer.parseInt(coords[0]);
                yPoint = Integer.parseInt(coords[1]);

                int distance = (int)(Math.pow(xPoint - getCircleX(), 2) + Math.pow(yPoint - getCircleY(), 2));
                if (distance < Math.pow(getCircleRadius(), 2)){
                    System.out.println("1");
                } else if (distance > Math.pow(getCircleRadius(), 2)) {
                    System.out.println("2");
                } else {
                    System.out.println("0");
                }
            }
        }
    }

    public static void setCircleX(String circleX) {
        task2.circleX = Integer.parseInt(circleX);
    }

    public static void setCircleY(String circleY) {
        task2.circleY = Integer.parseInt(circleY);
    }

    public static void setCircleRadius(String circleRadius) {
        task2.circleRadius = Integer.parseInt(circleRadius);
    }

    public static int getCircleX() {
        return circleX;
    }

    public static int getCircleY() {
        return circleY;
    }

    public static int getCircleRadius() {
        return circleRadius;
    }
}
