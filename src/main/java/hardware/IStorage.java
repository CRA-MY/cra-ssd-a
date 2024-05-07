package hardware;

public interface IStorage {
    public String Read(int position);
    public void Write(int position, String value);
}
