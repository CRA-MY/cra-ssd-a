package shell;

import shell.dto.UserInput;

import java.util.ArrayList;
import java.util.Arrays;


public class Validate {

    ArrayList<String> command ;

    UserInput userInput ;

    public Validate() {
        this.userInput = new UserInput();
        command = new ArrayList<>(
                Arrays.asList("write","read","fullwrite","fullread","exit","help")
        );
    }

    public UserInput validateComand(String str) {
        String[] checkStr = str.split(" ");
        if(checkStr.length<1 || !command.contains(checkStr[0]))  {
            userInput.setStatus("INVALID COMMAND");
            return userInput;
        }
        String cmd = checkStr[0];
        int cmdNum = command.indexOf(cmd);

        userInput.setCommend(cmd);
        if(cmdNum == 2 ){   //fullwrite
            validateValue(checkStr[2]);
        } else if(cmdNum == 1){      //read
            validateLBA(checkStr[1]);
        }else if(cmdNum == 0){        //write
            validateLBA(checkStr[1]);
            validateValue(checkStr[2]);
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
