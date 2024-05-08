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
        return setCommandAndRun(new ArrayList<>(Arrays.asList("R", String.valueOf(position))));
    }

    public String write(int position, String value) {
        return setCommandAndRun(new ArrayList<>(Arrays.asList("W", String.valueOf(position), value)));
    }

    public String erase(int position, int size) {
        return setCommandAndRun(new ArrayList<>(Arrays.asList("E", String.valueOf(position), String.valueOf(size))));
    }

    public String erase_range(int start, int end) {
        return setCommandAndRun(new ArrayList<>(Arrays.asList("E", String.valueOf(start), String.valueOf(end - start))));
    }

    public String help() {
        return Help.getHelp();
    }

    public String fullwrite(String value) {
        for (int i = 0; i < 100; i++) {
            write(i, value);
        }
        return null;
    }

    public String fullread() {
        for (int i = 0; i < 100; i++) {
            read(i);
        }
        return null;
    }

    public String testapp1(String value) {
        fullwrite(value);
        if (isWritten(value)) {
            logger.log("TestApp1 성공하였습니다.", false);
            return "PASS";
        }
        return "FAIL";
    }

    public String testapp2() {
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
