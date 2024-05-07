package shell;

import java.util.Scanner;

public class CommandShell {
    private Controller controller;
    private InputProvider inputProvider;

    public CommandShell(Controller controller, InputProvider inputProvider) {
        this.controller = controller;
        this.inputProvider = inputProvider;
    }

    public void run() {
        String input;
        do {
            System.out.print("Enter input: ");
            input = inputProvider.getInput(); // Scanner 대신 InputProvider 사용
            String returnValue = controller.getUserInput(input);
            System.out.println(returnValue);
        } while (!input.equals("exit"));
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        InputProvider scannerInputProvider = () -> new Scanner(System.in).nextLine();
        new CommandShell(controller, scannerInputProvider).run();
    }
}
