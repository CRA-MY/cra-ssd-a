package shell;

import hardware.IStorage;

public class Service {
    IStorage iStorage;

    public Service(IStorage iStorage) {
        this.iStorage = iStorage;
    }

    public void read(int position) {
        System.out.println(iStorage.Read(position));
    }

    public Boolean write(int position, String value) {
        return iStorage.Write(position, value);
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

    public void testapp1(String value) {
        fullwrite(value);
    }
}
