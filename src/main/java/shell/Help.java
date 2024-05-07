package shell;

public class Help {
    static void getHelp(){
        System.out.print("read(position) : 위치 입력 시 read\n");
        System.out.print("write(position, value) : 위치와 입력값 입력시 write\n");
        System.out.print("exit(position, value) : 종료\n");
        System.out.print("fullread() : 전체 read\n");
        System.out.print("fullwrite(value) : 입력값 전체 write\n");
    }
}
