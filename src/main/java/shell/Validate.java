package shell;

import shell.dto.UserInput;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Validate {
    public static final double MIN = 0;
    private static final double MAX = 99;
    public static final double SIZEMIN = 1;
    private static final double SIZEMAX = 10;
    private UserInput userInput;

    private  final Map<UserCommand, Function<String[], Boolean>> commandValidators = new HashMap<>();

     {
        commandValidators.put(UserCommand.READ, checkStr -> validateReadCommand(checkStr));
        commandValidators.put(UserCommand.WRITE, checkStr -> validateWriteCommand(checkStr));
        commandValidators.put(UserCommand.FULLWRITE, checkStr -> validateFullWriteCommand(checkStr));
        commandValidators.put(UserCommand.ERASE, checkStr -> validateEraseCommand(checkStr));
        commandValidators.put(UserCommand.ERASE_RANGE, checkStr -> validateEraseRangeCommand(checkStr));
        commandValidators.put(UserCommand.HELP, args -> true);
        commandValidators.put(UserCommand.FULLREAD, args -> true);
        commandValidators.put(UserCommand.TESTAPP1, args -> true);
        commandValidators.put(UserCommand.TESTAPP2, args -> true);
        commandValidators.put(UserCommand.FLUSH, args -> true);
    }

    public  UserInput validateCommand(String str) {
        String[] checkStr = str.trim().split("\\s+");
        String cmd = checkStr[0];
        userInput = new UserInput(cmd);
        if (cmd.isEmpty() || !isValidCmd(cmd, checkStr)) {
            return setInvalidCommand();
        }
        return userInput;
    }

    private boolean isValidCmd(String cmd, String[] checkStr) {
        UserCommand command = UserCommand.fromString(cmd);
        return commandValidators.getOrDefault(command, args -> false).apply(checkStr);
    }

    private boolean validateEraseRangeCommand(String[] checkStr) {
        if (checkStr.length != 3) return false;

        int lba = validateLBARange(checkStr[1]);
        int elba = validateLBARange(checkStr[2]);
        if (lba == -1 || elba == -1) return false;

        userInput.setLBA(lba);
        userInput.setELBA(elba);

        return true;
    }

    private boolean validateEraseCommand(String[] checkStr) {
        if (checkStr.length != 3) return false;

        int lba = validateLBARange(checkStr[1]);
        int size = validateSizeRange(checkStr[2]);

        if (lba == -1 || size == -1) return false;

        userInput.setLBA(lba);
        userInput.setSize(size);

        return true;
    }

    private boolean validateReadCommand(String[] checkStr) {
        if (checkStr.length != 2) return false;

        int ret = validateLBARange(checkStr[1]);
        if (ret==-1) return false;

        userInput.setLBA(ret);
        return true;
    }

    private boolean validateWriteCommand(String[] checkStr) {
         if (checkStr.length != 3) return false;
         int retLBA = validateLBARange(checkStr[1]);
         boolean retVal = validateValue(checkStr[2]);

         if(retLBA != -1 && retVal){
             userInput.setLBA(retLBA);
             userInput.setValue(checkStr[2]);
             return true;
         }else{
             return false;
         }
    }

    private boolean validateFullWriteCommand(String[] checkStr) {
         if(checkStr.length != 2) return false;
         if(validateValue(checkStr[1])){
             userInput.setValue(checkStr[1]);
             return true;
         }
        return false;
    }

    private int validateSizeRange(String number) {
        try {
            int result = Integer.parseInt(number);
            if(SIZEMIN <= result && result <= SIZEMAX){
                return result;
            }else{
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    private int validateLBARange(String number) {
        try {
            int result = Integer.parseInt(number);
            if(MIN <= result && result <= MAX){
                return result;
            }else{
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean validateValue(String value) {
        if (!value.startsWith("0x") || value.length() != 10) {
            return false;
        }

        for (int i = 2; i < value.length(); i++) {
            char c = value.toLowerCase().charAt(i);
            if ('0' > c || c > 'f') return false;
        }

        return true;
    }

    private UserInput setInvalidCommand() {
        userInput.setStatus("INVALID COMMAND");
        return userInput;
    }
}
