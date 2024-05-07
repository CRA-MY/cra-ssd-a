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
        for (int i = 0; i < 100; i++) {
            if (!iStorage.Read(i).equals(value)) {
                System.out.print("TestApp1 실패.\n");
                return;
            }
        }
        System.out.print("TestApp1 성공하였습니다.\n");
    }

    public void testapp2() {
        String testapp2WriteValue = "0xAAAABBBB";
        String testapp2OverWriteValue = "0x12345678";

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 30; j++) {
                write(i, testapp2WriteValue);
            }
        }

        for (int i = 0; i < 5; i++) {
            write(i, testapp2OverWriteValue);
        }

        for (int i = 0; i < 5; i++) {
            if (!iStorage.Read(i).equals(testapp2OverWriteValue)) {
                System.out.print("TestApp2 실패.\n");
                return;
            }
        }

        System.out.print("TestApp2 성공하였습니다.\n");
    }

}
