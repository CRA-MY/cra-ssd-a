package shell.dto;

public class UserInput {
    String commend;
    int LBA;
    String value;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public UserInput(String commend, int LBA, String value) {
        this.commend = commend;
        this.LBA = LBA;
        this.value = value;
    }

    public UserInput() {

    }

    public String getCommend() {
        return commend;
    }

    public void setCommend(String commend) {
        this.commend = commend;
    }

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
