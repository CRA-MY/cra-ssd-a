package shell;

import shell.dto.UserInput;

public enum UserCommand {
    READ("read") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.printer(controller.service.read(userInput.getLBA()));
        }
    },
    WRITE("write") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.service.write(userInput.getLBA(), userInput.getValue());
        }
    },
    HELP("help") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.printer(controller.service.help());
        }
    },
    FULLWRITE("fullwrite") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.service.fullwrite(userInput.getValue());
        }
    },
    FULLREAD("fullread") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.printer(controller.service.fullread());
        }
    },
    TESTAPP1("testapp1") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.service.testapp1(Controller.DEFAULT_TESTAPP1_VALUE);
        }
    },
    TESTAPP2("testapp2") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.service.testapp2();
        }
    },
    FLUSH("flush") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.service.flush();
        }
    },
    ERASE("erase") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.service.erase(userInput.getLBA(), userInput.getSize());
        }
    },
    ERASE_RANGE("erase_range") {
        @Override
        public void execute(Controller controller, UserInput userInput) {
            controller.service.erase_range(userInput.getLBA(), userInput.getELBA());
        }
    };

    private final String command;

    UserCommand(String command) {
        this.command = command;
    }

    public abstract void execute(Controller controller, UserInput userInput);

    public static UserCommand fromString(String command) {
        for (UserCommand c : UserCommand.values()) {
            if (c.command.equalsIgnoreCase(command)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + command);
    }
}
