package shell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {

    //write  3  0xAAAABBBB
    //read  3
    //fullwrite 0xABCDFFFF
    //INVALID COMMAND

    /*    Read 명령어와 Write 명령어만 존재
    • LBA 단위는 4 Byte
    • LBA 0 ~ 99 까지 100 칸을 저장할 수 있다.
    항상 0x가 붙으며10 글자로표기한다. ( 0x00000000  ~  0xFFFFFFFF )*/

    enum command{
        write,
        read,
        fullwrite,
        fullread,
        exit,
        help
    }

    @Test
    void isNotNull(){
        Validate validate = new Validate();
        assertNotNull(validate);
    }

}