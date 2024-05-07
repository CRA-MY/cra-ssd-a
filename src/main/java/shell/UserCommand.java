package shell;

public enum UserCommand {
    READ("read"),
    WRITE("write"),
    HELP("help"),
    FULLWRITE("fullwrite"),
    FULLREAD("fullread");

    private final String command;

    UserCommand(String command) {
        this.command = command;
    }

    public static UserCommand fromString(String command) {
        for (UserCommand c : UserCommand.values()) {
            if (c.command.equalsIgnoreCase(command)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + command);
    }
}
