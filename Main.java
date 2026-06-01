import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cipher cipher = new Cipher();
        FileProcessor fileProcessor = new FileProcessor();

        while (true) {
            System.out.println("1. Зашифровать\n2. Расшифровать\n3. Brute Force\n4. Статистический анализ\n0. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            System.out.println("Введите путь к исходному файлу:");
            String inputPath = scanner.nextLine();
            if (!Files.exists(Path.of(inputPath))) {
                System.out.println("Файл не найден.");
                continue;
            }

            System.out.println("Введите путь к файлу назначения:");
            String outputPath = scanner.nextLine();

            try {
                String content = fileProcessor.readFile(inputPath);
                String result = "";

                if (choice == 1) {
                    System.out.println("Введите ключ:");
                    int key = scanner.nextInt();
                    result = cipher.encrypt(content, key);
                } else if (choice == 2) {
                    System.out.println("Введите ключ:");
                    int key = scanner.nextInt();
                    result = cipher.decrypt(content, key);
                } else if (choice == 3) {
                    BruteForce bruteForce = new BruteForce();
                    int foundKey = bruteForce.findKey(content);
                    System.out.println("Найден ключ: " + foundKey);
                    result = cipher.decrypt(content, foundKey);
                } else if (choice == 4) {
                    StatisticAnalyzer analyzer = new StatisticAnalyzer();
                    int foundKey = analyzer.findKey(content);
                    System.out.println("Предполагаемый ключ: " + foundKey);
                    result = cipher.decrypt(content, foundKey);
                }

                fileProcessor.writeFile(outputPath, result);
                System.out.println("Успешно выполнено.");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
