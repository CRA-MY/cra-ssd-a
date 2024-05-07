package shell;

import shell.dto.UserInput;

public class Validate {
    private UserInput userInput;

    public Validate() {
        this.userInput = new UserInput();
    }

    public UserInput validateCommand(String str) {
        String[] checkStr = str.trim().split("\\s+");
        if (checkStr.length < 1 || checkStr[0].equals("")) {
            return setInvalidCommand();
        }

        String cmd = checkStr[0];
        UserCommand command = UserCommand.fromString(cmd);
        if (command == null) {
            return setInvalidCommand();
        }

        userInput.setCommand(cmd);
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
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean validateValue(String value) {
        if (!value.startsWith("0x") || value.length() != 10) {
            return false;
        }
        return true;
    }

    private UserInput setInvalidCommand() {
        userInput.setStatus("INVALID COMMAND");
        return userInput;
    }
}