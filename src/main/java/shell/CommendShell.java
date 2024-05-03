package shell;

import java.util.Scanner;

public class CommendShell {
    public static void main(String[] args) {
        String input;
        do {
            input = getInput();
        } while (!input.equals("exit"));
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input: ");
        return scanner.nextLine();
    }
}
