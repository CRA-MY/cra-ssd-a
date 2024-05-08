package shell;

public class Help {
    static String getHelp(){
        String helpMessage = "";
        helpMessage += "read position : 위치(position) 입력 시 read\n";
        helpMessage += "write position value : 위치(position) 입력값(value) 입력시 write\n";
        helpMessage += "erase position size : 위치(position) 크기(size) 입력시 erase\n";
        helpMessage += "erase_range start end : 시작 위치(start) 종료 위치(둥) 입력시 erase\n";
        helpMessage += "exit : 종료\n";
        helpMessage += "fullread : 전체 read\n";
        helpMessage += "fullwrite value : 입력값(value) 전체 write\n";
        helpMessage += "testapp1 : full write와 full read Test\n";
        helpMessage += "testapp2 : write와 overwrte, read Test";
        return helpMessage;
    }
}
