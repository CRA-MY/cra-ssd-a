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
        if (userInput.getCommend().equals("read")) {
            service.read(userInput.getLBA());
        }
        else if (userInput.getCommend().equals("write")) {
            service.write(userInput.getLBA(), userInput.getValue());
        }
        else if (userInput.getCommend().equals("help")) {
            service.help();
        }
        else if (userInput.getCommend().equals("fullwrite")) {
            service.fullwrite(userInput.getValue());
        }
        else if (userInput.getCommend().equals("fullread")) {
            service.fullread();
        }
    }
}
