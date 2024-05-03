package shell;

import hardware.IStorage;

public class Service {
    IStorage iStoreage;
    public Service(IStorage iStoreage) {
        this.iStoreage = iStoreage;
    }

}
