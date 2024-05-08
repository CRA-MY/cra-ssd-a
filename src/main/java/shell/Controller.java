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
        UserCommand command = UserCommand.fromString(userInput.getCommand());
        Boolean result = true;
        switch (command) {
            case READ:
                printer(service.read(userInput.getLBA()));
                break;
            case WRITE:
                service.write(userInput.getLBA(), userInput.getValue());
                break;
            case ERASE:
                service.erase(userInput.getLBA(), userInput.getSize());
                break;
            case ERASE_RANGE:
                service.erase_range(userInput.getLBA(), userInput.getELBA());
                break;
            case HELP:
                printer(service.help());
                break;
            case FULLWRITE:
                service.fullwrite(userInput.getValue());
                break;
            case FULLREAD:
                printer(service.fullread());
                break;
            case TESTAPP1:
                service.testapp1(DEFAULT_TESTAPP1_VALUE);
                break;
            case TESTAPP2:
                service.testapp2();
                break;
            default:
                throw new IllegalArgumentException("Unsupported command: " + command);
        }
        return result;
    }

    public void printer(String s) {
        if (isPrint) {
            System.out.println(s);
        }
    }
}
