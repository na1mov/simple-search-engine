package src;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Index index = new Index("files", 3);

        while (true) {
            System.out.print("Введи запрос: ");
            String query = scanner.nextLine();
            List<Integer> serp = index.search(query);
            System.out.println(serp);
        }
    }
}
