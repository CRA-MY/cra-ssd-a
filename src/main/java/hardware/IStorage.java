package hardware;

public interface IStorage {
    public String Read(int position);
    public Boolean Write(int position, String value);
}
