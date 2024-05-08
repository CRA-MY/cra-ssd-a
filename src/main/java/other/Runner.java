package other;

import common.Logger;
import hardware.SSDManager;
import shell.Controller;
import shell.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Runner {
    private static Logger logger = Logger.getInstance();

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.log("실행 인자가 필요합니다. Run config에서 Args를 입력후 실행해주세요.", true);
            return;
        }

        String filePath = "./run_list/" + args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.print(line + " --- Run...");
                boolean result = runCommand(line); // 함수 실행
                if (result) {
                    System.out.print(" PASS\n");
                } else {
                    System.out.print(" FAIL\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean runCommand(String command) {
        Controller controller = new Controller(new Service(new SSDManager()), false);
        return controller.receiveUserInputString(command);
    }
}
