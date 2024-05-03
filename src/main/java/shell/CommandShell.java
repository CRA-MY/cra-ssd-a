package shell;

import java.util.Scanner;

public class CommandShell {
    private Controller controller;

    public CommandShell(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print("Enter input: ");
            input = scanner.nextLine();
            controller.getUserInput(input);
        } while (!input.equals("exit"));
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        new CommandShell(controller).run();
    }
}
