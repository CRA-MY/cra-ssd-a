package shell;

import hardware.IStorage;

public class Service {
    IStorage iStoreage;

    public Service(IStorage iStoreage) {
        this.iStoreage = iStoreage;
    }

    public void read(int position) {
        System.out.println(iStoreage.Read(position));
    }

    public Boolean write(int position, String value) {
        return iStoreage.Write(position, value);
    }


    public void help() {
        Help.getHelp();
    }

    public void fullwrite(String value) {
        for (int i = 0; i < 100; i++) {
            write(i, value);
        }
    }

    public void fullread() {
        for (int i = 0; i < 100; i++) {
            read(i);
        }
    }
}
