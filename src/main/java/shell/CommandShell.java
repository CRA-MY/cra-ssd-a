package shell;

import common.Logger;
import hardware.SSDManager;

import java.io.IOException;
import java.util.Scanner;

public class CommandShell {
    private final Logger logger = Logger.getInstance();

    private Controller controller;
    private InputProvider inputProvider;

    public CommandShell(Controller controller, InputProvider inputProvider) {
        this.controller = controller;
        this.inputProvider = inputProvider;
    }

    public void run() {
        String input;
        logger.log("애플리케이션이 시작되었습니다.", true);
        while (true) {
            System.out.print("Enter input: ");
            input = inputProvider.getInput(); // Scanner 대신 InputProvider 사용
            logger.log("받은 입력값: " + input, true);
            if (input.equals("exit")) break;
            controller.receiveUserInputString(input);
        }
        logger.log("애플리케이션이 종료되었습니다.", true);
    }

    public static void main(String[] args) {
        Controller controller = new Controller(new Service(new SSDManager()));
        InputProvider scannerInputProvider = () -> new Scanner(System.in).nextLine();
        new CommandShell(controller, scannerInputProvider).run();
    }
}
