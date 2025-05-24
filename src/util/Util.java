package util;

import java.util.Scanner;

public class Util {
    public static int userId = 1;
    public static int todoId = 4;
    public static int currentUserId = 0;

    public static Scanner intScanner = new Scanner(System.in);
    public static Scanner doubleScanner = new Scanner(System.in);

    public static int getNum(String text) {
        System.out.printf("%s: ", text);
        return intScanner.nextInt();
    }

    public static String getStr(String text) {
        System.out.printf("%s: ", text);
        return doubleScanner.nextLine();
    }
}
