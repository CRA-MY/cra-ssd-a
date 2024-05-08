package shell.dto;

public class UserInput {
    String command;
    int LBA;
    String value;
    String status;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size;

    public int getELBA() {
        return ELBA;
    }

    public void setELBA(int ELBA) {
        this.ELBA = ELBA;
    }

    int ELBA;

    public UserInput(String cmd) {
        this.command = cmd;
        this.status = "PASS";
        size = 0;
    }

    public UserInput(String command, int LBA, String value) {
        this.command = command;
        this.LBA = LBA;
        this.value = value;
    }

    public UserInput(String command, int LBA, String value, String status) {
        this.command = command;
        this.LBA = LBA;
        this.value = value;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) { this.command = command; }

    public int getLBA() {
        return LBA;
    }

    public void setLBA(int LBA) {
        this.LBA = LBA;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
