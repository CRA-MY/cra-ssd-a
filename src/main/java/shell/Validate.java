package shell;

import shell.dto.UserInput;

import java.util.ArrayList;
import java.util.Arrays;


public class Validate {
    UserInput userInput ;

    public Validate() {
        this.userInput = new UserInput();
    }

    public UserInput validateComand(String str) {
        String[] checkStr = str.split(" ");
        if(checkStr.length<1)  {
            userInput.setStatus("INVALID COMMAND");
            return userInput;
        }

        String cmd = checkStr[0];
        userInput.setCommand(cmd);
        UserCommand command = UserCommand.fromString(cmd);
        switch (command){
            case READ:
                validateLBA(checkStr[1]);
                break;
            case WRITE:
                validateLBA(checkStr[1]);
                validateValue(checkStr[2]);
                break;
            case FULLWRITE:
                validateValue(checkStr[1]);
                break;
            default:
                userInput.setStatus("INVALID COMMAND");
        }
        return userInput;
    }


    public boolean  validateLBA(String LBA) {
        int result=-1;
        try{
            result = Integer.parseInt(LBA);
            if(result>99 || result<0) {
                userInput.setStatus("INVALID COMMAND");
                return false;
            }
            userInput.setLBA(result);
            return true;
        }catch (Exception e){
            userInput.setStatus("INVALID COMMAND");
            return false;
        }
    }

    public boolean validateValue(String value) {
        //항상 0x가 붙으며10 글자로표기한다. ( 0x00000000  ~  0xFFFFFFFF )
        if (!value.startsWith("0x")) {
            userInput.setStatus("INVALID COMMAND");
            return false;
        }
        if (!(value.length()==10)) {
            userInput.setStatus("INVALID COMMAND");
            return false;
        }
        return true;
    }
}
