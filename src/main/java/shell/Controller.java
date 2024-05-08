package shell;

import shell.dto.UserInput;

public class Controller {
    public static final String DEFAULT_TESTAPP1_VALUE = "0xAAAABBBB";

    Service service;
    Validate validate;

    public Controller(Service service) {
        this.service = service;
        this.validate = new Validate();
    }

    public boolean receiveUserInputString(String userInputString) {
        UserInput userInput;
        try {
            userInput = validate.validateCommand(userInputString);
            if (userInput.getStatus().equals("INVALID COMMAND")) {
                System.out.println("INVALID COMMAND");
                return false;
            }
        } catch (Exception e) {
            System.out.println("INVALID COMMAND");
            return false;
        }

        sendService(userInput);
        return true;
    }

    public void sendService(UserInput userInput) {
        UserCommand command = UserCommand.fromString(userInput.getCommand());
        switch (command) {
            case READ:
                service.read(userInput.getLBA());
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
                service.help();
                break;
            case FULLWRITE:
                service.fullwrite(userInput.getValue());
                break;
            case FULLREAD:
                service.fullread();
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
    }
}
