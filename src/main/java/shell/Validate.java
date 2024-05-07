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
            //validateValue();
        } else if(cmdNum == 1){      //read
            //validateLBA();
        }else if(cmdNum == 0){        //write
            //validateLBA();
            //validateValue();
        }
        return userInput;
    }


    public int  validateLBA(String LBA) {
        int result=-1;
        try{
            result = Integer.parseInt(LBA);
            if(result>99 || result<0) {
                userInput.setStatus("INVALID COMMAND");
            }
            userInput.setLBA(result);
        }catch (Exception e){
            userInput.setStatus("INVALID COMMAND");
        }
        return result;
    }
}
