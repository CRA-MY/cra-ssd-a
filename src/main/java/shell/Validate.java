package shell;

import shell.dto.UserInput;

public class Validate {
    private UserInput userInput;

    public UserInput validateCommand(String str) {
        String[] checkStr = str.trim().split("\\s+");

        String cmd = checkStr[0];
        userInput = new UserInput(cmd);
        if (checkStr.length < 1 || cmd.equals("")) {
            return setInvalidCommand();
        }

        UserCommand command = UserCommand.fromString(cmd);
        boolean isValid = false;
        switch (command) {
            case READ :
                isValid = validateReadCommand(checkStr);
                break;
            case WRITE :
                isValid = validateWriteCommand(checkStr);
                break;
            case FULLWRITE :
                isValid = validateFullWriteCommand(checkStr);
                break;
            case HELP:
            case FULLREAD:
                isValid = true;
                break;
            default :
                isValid = false;
        };

        if (!isValid) {
            return setInvalidCommand();
        }
        return userInput;
    }

    private boolean validateReadCommand(String[] checkStr) {
        if (checkStr.length < 2) return false;
        return validateLBA(checkStr[1]);
    }

    private boolean validateWriteCommand(String[] checkStr) {
        if (checkStr.length < 3) return false;
        return validateLBA(checkStr[1]) && validateValue(checkStr[2]);
    }

    private boolean validateFullWriteCommand(String[] checkStr) {
        if (checkStr.length < 2) return false;
        return validateValue(checkStr[1]);
    }

    boolean validateLBA(String LBA) {
        try {
            int result = Integer.parseInt(LBA);
            if (result > 99 || result < 0) {
                setInvalidCommand();
                return false;
            }
            userInput.setLBA(result);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean validateValue(String value) {
        if (!value.startsWith("0x") || value.length() != 10) {
            return false;
        }

        for(int i=2; i<value.length(); i++){
            if('0' > value.toLowerCase().charAt(i) || value.toLowerCase().charAt(i)>'f' )
                return false;
        }
        userInput.setValue(value);
        return true;
    }

    private UserInput setInvalidCommand() {
        userInput.setStatus("INVALID COMMAND");
        return userInput;
    }
}