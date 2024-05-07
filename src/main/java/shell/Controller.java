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

    public void receiveUserInputString(String userInputString) {
        UserInput userInput;
        try {
            userInput = validate.validateComand(userInputString);
            if (userInput.getStatus().equals("INVALID COMMAND")) {
                System.out.println("INVALID COMMAND");
                return;
            }
        } catch (Exception e) {
            System.out.println("INVALID COMMAND");
            return;
        }

        sendService(userInput);
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
            case TESTAPP2:
                service.testapp2();
            default:
                throw new IllegalArgumentException("Unsupported command: " + command);
        }
    }
}
