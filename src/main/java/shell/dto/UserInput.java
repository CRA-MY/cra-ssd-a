package shell.dto;

public class UserInput {
    String commend;
    String LBA;
    String value;

    public UserInput(String commend, String LBA, String value) {
        this.commend = commend;
        this.LBA = LBA;
        this.value = value;
    }

    public String getCommend() {
        return commend;
    }

    public void setCommend(String commend) {
        this.commend = commend;
    }

    public String getLBA() {
        return LBA;
    }

    public void setLBA(String LBA) {
        this.LBA = LBA;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
