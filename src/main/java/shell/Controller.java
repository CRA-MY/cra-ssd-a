package shell;

import shell.dto.UserInput;

public class Controller {
    public static final String DEFAULT_TESTAPP1_VALUE = "0xAAAABBBB";

    Service service;
    Validate validate;
    Boolean isPrint;

    public Controller(Service service, Boolean isPrint) {
        this.service = service;
        this.validate = new Validate();
        this.isPrint = isPrint;
    }

    public boolean receiveUserInputString(String userInputString) {
        UserInput userInput;
        try {
            userInput = validate.validateCommand(userInputString);
            if (userInput.getStatus().equals("INVALID COMMAND")) {
                printer("INVALID COMMAND");
                return false;
            }
        } catch (Exception e) {
            printer("INVALID COMMAND");
            return false;
        }
        return sendService(userInput);
    }

    public Boolean sendService(UserInput userInput) {
        try {
            UserCommand command = UserCommand.fromString(userInput.getCommand());
            command.execute(this, userInput);
            return true;
        } catch (IllegalArgumentException e) {
            printer("Unsupported command: " + userInput.getCommand());
            return false;
        }
    }

    public void printer(String s) {
        if (isPrint) {
            System.out.println(s);
        }
    }
}
