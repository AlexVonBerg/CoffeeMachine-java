import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dayNumber = scanner.nextInt();

        Weekday day = Weekday.values()[dayNumber - 1];
        System.out.println(day);
    }
}

enum Weekday {
    // Define the enum constants here
}