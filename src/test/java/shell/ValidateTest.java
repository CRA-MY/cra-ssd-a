package shell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import shell.dto.UserInput;

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

    @BeforeEach
    void setUp() {
        validate = new Validate();
    }

    Validate validate ;

    @Mock
    UserInput userInput;

    @Test
    void isNotNull(){
        assertNotNull(validate);
    }

    @Test
    void validateComand(){
        String str = "write  3  0xAAAABBBB";
        Validate validate = new Validate();
        validate.validateComand(str);
    }

    @Test
    void validateLBA(){
        int ret = validate.validateLBA("1");
        assertEquals(2,ret );
    }


}