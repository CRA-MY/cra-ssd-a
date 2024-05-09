package shell;

import common.Logger;
import shell.dto.UserInput;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Validate {
    public static final int MIN = 0;
    private static final int MAX = 99;
    public static final int SIZEMIN = 1;
    private static final int SIZEMAX = 10;
    private static final int VALUE_LENGTH = 10;
    private static final String VALUE_PREFIX = "0x";
    private UserInput userInput;
    private final Logger logger = Logger.getInstance();
    private final Map<UserCommand, Function<String[], Boolean>> commandValidators = new HashMap<>();

    {
        commandValidators.put(UserCommand.READ, this::validateReadCommand);
        commandValidators.put(UserCommand.WRITE, this::validateWriteCommand);
        commandValidators.put(UserCommand.FULLWRITE, this::validateFullWriteCommand);
        commandValidators.put(UserCommand.ERASE, this::validateCommand);
        commandValidators.put(UserCommand.ERASE_RANGE, this::validateCommand);
        commandValidators.put(UserCommand.HELP, args -> true);
        commandValidators.put(UserCommand.FULLREAD, args -> true);
        commandValidators.put(UserCommand.TESTAPP1, args -> true);
        commandValidators.put(UserCommand.TESTAPP2, args -> true);
        commandValidators.put(UserCommand.FLUSH, args -> true);
    }

    public UserInput validateCommand(String str) {
        logger.log(str + " validation start", false);
        String[] checkStr = str.trim().split("\\s+");
        String cmd = checkStr[0];
        userInput = new UserInput(cmd);
        if (cmd.isEmpty() || !isValidCmd(cmd, checkStr)) {
            logger.log(str + " validation fail", false);
            return setInvalidCommand();
        }
        logger.log(str + " validation pass", false);
        return userInput;
    }

    private boolean isValidCmd(String cmd, String[] checkStr) {
        UserCommand command = UserCommand.fromString(cmd);
        return commandValidators.getOrDefault(command, args -> false).apply(checkStr);
    }

    private boolean validateCommand(String[] checkStr) {
        if (checkStr.length != 3) return false;

        int firstParameter = validateRange(checkStr[1], MIN, MAX);
        int sParam;

        UserCommand command2 = UserCommand.fromString(checkStr[0]);
        if (command2.equals(UserCommand.ERASE)) {
            sParam = validateRange(checkStr[2], SIZEMIN, SIZEMAX);
            userInput.setSize(sParam);
        } else {
            sParam = validateRange(checkStr[2], MIN, MAX);
            userInput.setELBA(sParam);
        }
        if (firstParameter == -1 || sParam == -1) return false;
        userInput.setLBA(firstParameter);

        return true;
    }

    private boolean validateReadCommand(String[] checkStr) {
        if (checkStr.length != 2) return false;

        int ret = validateRange(checkStr[1], MIN, MAX);
        if (ret == -1) return false;

        userInput.setLBA(ret);
        return true;
    }

    private boolean validateWriteCommand(String[] checkStr) {
        if (checkStr.length != 3) return false;
        int retLBA = validateRange(checkStr[1], MIN, MAX);
        boolean retVal = validateValue(checkStr[2]);

        if (retLBA != -1 && retVal) {
            userInput.setLBA(retLBA);
            userInput.setValue(checkStr[2]);
            return true;
        } else {
            return false;
        }
    }

    private boolean validateFullWriteCommand(String[] checkStr) {
        if (checkStr.length != 2) return false;
        if (validateValue(checkStr[1])) {
            userInput.setValue(checkStr[1]);
            return true;
        }
        return false;
    }

    private int validateRange(String number, int min, int max) {
        try {
            int result = Integer.parseInt(number);
            if (min <= result && result <= max) {
                return result;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean validateValue(String value) {
        return value.startsWith(VALUE_PREFIX) && value.length() == VALUE_LENGTH
                && value.substring(2).toLowerCase().matches("[0-9a-f]+");
    }

    private UserInput setInvalidCommand() {
        userInput.setStatus("INVALID COMMAND");
        return userInput;
    }
}
