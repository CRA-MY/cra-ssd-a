package hardware;

public class SSDManager implements IStorage {
    SSDFileReader ssdFileReader;
    SSDFileWriter ssdFileWriter;

    public SSDManager() {
        this.ssdFileReader = new SSDFileReader();
        this.ssdFileWriter = new SSDFileWriter();
    }

    public SSDManager(SSDFileReader ssdFileReader, SSDFileWriter ssdFileWriter) {
        this.ssdFileReader = ssdFileReader;
        this.ssdFileWriter = ssdFileWriter;
    }

    @Override
    public String Read(int position) {
        return ssdFileReader.read(position);
    }

    @Override
    public void Write(int position, String value) {
        return ssdFileWriter.write(position, value);
    }
}
