package shell;

import shell.dto.UserInput;

public class Controller {
    Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public String getUserInput(String input) {
        return null;
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
            default:
                throw new IllegalArgumentException("Unsupported command: " + command);
        }
    }
}
