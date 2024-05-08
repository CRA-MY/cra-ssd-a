package shell;

public class Help {
    static void getHelp(){
        System.out.print("read position : 위치(position) 입력 시 read\n");
        System.out.print("write position value : 위치(position) 입력값(value) 입력시 write\n");
        System.out.print("erase position size : 위치(position) 크기(size) 입력시 erase\n");
        System.out.print("erase_range start end : 시작 위치(start) 종료 위치(둥) 입력시 erase\n");
        System.out.print("exit : 종료\n");
        System.out.print("fullread : 전체 read\n");
        System.out.print("fullwrite value : 입력값(value) 전체 write\n");
        System.out.print("testapp1 : full write와 full read Test\n");
        System.out.print("testapp2 : write와 overwrte, read Test\n");
    }
}
