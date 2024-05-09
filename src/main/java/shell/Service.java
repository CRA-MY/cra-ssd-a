package shell;

import common.Logger;
import hardware.IStorage;

import java.util.ArrayList;
import java.util.Arrays;

public class Service {
    private final Logger logger = Logger.getInstance();

    public static final String TESTAPP_2_INITIAL_WRITE_VALUE = "0xAAAABBBB";
    public static final String TESTAPP_2_OVER_WRITE_VALUE = "0x12345678";
    IStorage iStorage;

    public Service(IStorage iStorage) {
        this.iStorage = iStorage;
    }

    public String read(int position) {
        logger.log(position + "위치 read", false);
        return setCommandAndRun(new ArrayList<>(Arrays.asList("R", String.valueOf(position))));
    }

    public String write(int position, String value) {
        logger.log(position + " 위치에" + value + " write", false);
        return setCommandAndRun(new ArrayList<>(Arrays.asList("W", String.valueOf(position), value)));
    }

    public String erase(int position, int size) {
        logger.log(position + " 부터" + size + "개 erase", false);
        return setCommandAndRun(new ArrayList<>(Arrays.asList("E", String.valueOf(position), String.valueOf(size))));
    }

    public String erase_range(int start, int end) {
        logger.log(start + " 부터" + end + " erase", false);
        return setCommandAndRun(new ArrayList<>(Arrays.asList("E", String.valueOf(start), String.valueOf(end - start))));
    }

    public String help() {
        logger.log("help 호출", false);
        return Help.getHelp();
    }

    public String fullwrite(String value) {
        logger.log("ssd로 fullwrite 요청", false);
        for (int i = 0; i < 100; i++) {
            write(i, value);
        }
        return null;
    }

    public String fullread() {
        logger.log("ssd로 fullread 요청", false);
        String result = "";
        for (int i = 0; i < 100; i++) {
            result += read(i) + "\n";
        }
        return result;
    }

    public String testapp1(String value) {
        logger.log("TestApp1 시작하였습니다.", false);
        fullwrite(value);
        if (isWritten(value)) {
            logger.log("TestApp1 성공하였습니다.", false);
            return "PASS";
        }
        return "FAIL";
    }

    public String testapp2() {
        logger.log("TestApp2 시작하였습니다.", false);
        initialWrite30times(TESTAPP_2_INITIAL_WRITE_VALUE);
        overWrite(TESTAPP_2_OVER_WRITE_VALUE);
        if (isOverWritten(TESTAPP_2_OVER_WRITE_VALUE)) {
            logger.log("TestApp2 성공하였습니다.", false);
            return "PASS";
        }
        return "FAIL";
    }

    private String setCommandAndRun(ArrayList<String> tempCommand) {
        iStorage.setCommand(tempCommand);
        return iStorage.run();
    }

    private boolean isWritten(String value) {
        for (int i = 0; i < 100; i++) {
            if (!read(i).equals(value)) {
                logger.log("TestApp1 실패.", false);
                logger.log(i + "번 LBA에 " + value + "가 정상 Write 되지 않았습니다.\n", false);
                return false;
            }
        }
        return true;
    }

    private void initialWrite30times(String value) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 30; j++) {
                write(i, value);
            }
        }
    }

    private boolean isOverWritten(String value) {
        for (int i = 0; i < 5; i++) {
            if (!read(i).equals(value)) {
                logger.log("TestApp2 실패.", false);
                logger.log(i + "번 LBA에 " + value + "가 정상 Over Write 되지 않았습니다.", false);
                return false;
            }
        }
        return true;
    }

    private void overWrite(String value) {
        for (int i = 0; i < 5; i++) {
            write(i, value);
        }
    }

}
