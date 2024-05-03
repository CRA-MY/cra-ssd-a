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

    public void exit() {

        System.exit(0);
    }

    public void help() {
        Help.getHelp();
    }

    public void fullwrite(String value) {
        for(int i=0;i<100;i++){
            write(i,value);
        }
    }

    public String fullread() {
        String allReslut="";
        for(int i=0;i<100;i++){
            allReslut+=read(i);
            allReslut+="\n";
        }
        return allReslut;
    }
}
