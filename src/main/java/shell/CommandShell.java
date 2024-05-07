package shell;

import hardware.SSDManager;

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
            controller.getUserInput(input);
        } while (!input.equals("exit"));
    }

    public static void main(String[] args) {
        Controller controller = new Controller(new Service(new SSDManager()));
        InputProvider scannerInputProvider = () -> new Scanner(System.in).nextLine();
        new CommandShell(controller, scannerInputProvider).run();
    }
}
