package shell;

import hardware.IStorage;

public class Service {
    IStorage iStoreage;

    public Service(IStorage iStoreage) {
        this.iStoreage = iStoreage;
    }

    public String read(int position) {
        return iStoreage.Read(position);
    }

    public Boolean write(int position, String value) {
        return iStoreage.Write(position, value);
    }
}
