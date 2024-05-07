package shell;

import hardware.IStorage;

public class Service {
    public static final String TESTAPP_2_INITIAL_WRITE_VALUE = "0xAAAABBBB";
    public static final String TESTAPP_2_OVER_WRITE_VALUE = "0x12345678";
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
        if (isWritten(value)) {
            System.out.print("TestApp1 성공하였습니다.\n");
        }
    }

    private boolean isWritten(String value) {
        for (int i = 0; i < 100; i++) {
            if (!iStorage.Read(i).equals(value)) {
                System.out.print("TestApp1 실패.\n");
                System.out.print(i + "번 LBA에 " + value + "가 정상 Write 되지 않았습니다.\n");
                return false;
            }
        }
        return true;
    }

    public void testapp2() {
        initialWrite30times(TESTAPP_2_INITIAL_WRITE_VALUE);
        overWrite(TESTAPP_2_OVER_WRITE_VALUE);
        if (isOverWritten(TESTAPP_2_OVER_WRITE_VALUE)) {
            System.out.print("TestApp2 성공하였습니다.\n");
        }
    }

    private void initialWrite30times(String value) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 30; j++) {
                write(i, value);
            }
        }
    }

    private boolean isOverWritten(String value) {
        for (int i = 0; i < 5; i++) {
            if (!iStorage.Read(i).equals(value)) {
                System.out.print("TestApp2 실패.\n");
                System.out.print(i + "번 LBA에 " + value + "가 정상 Over Write 되지 않았습니다.\n");
                return false;
            }
        }
        return true;
    }

    private void overWrite(String value) {
        for (int i = 0; i < 5; i++) {
            write(i, value);
        }
    }

}
