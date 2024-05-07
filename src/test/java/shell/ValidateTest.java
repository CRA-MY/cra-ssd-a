package shell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import shell.dto.UserInput;

import java.util.concurrent.atomic.AtomicReference;

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

    Validate validate ;

    @BeforeEach
    void setUp() {
        validate = new Validate();
    }

    @Mock
    UserInput userInput;

    @Test
    void isNotNull(){
        assertNotNull(validate);
    }

    @Test
    void testValidateCommandWithEmptyString() {
        UserInput result = validate.validateCommand("");
        assertEquals("INVALID COMMAND", result.getStatus());
    }

    @Test
    void testValidateCommandWithInvalidCommand() {
        AtomicReference<UserInput> result = null;
        IllegalArgumentException thrown = (IllegalArgumentException) assertThrows(Exception.class, () -> {
            result.set(validate.validateCommand("INVALID"));
        });

        assertEquals("Unknown command: INVALID", thrown.getMessage() );
    }

    @Test
    void testValidateReadCommandWithValidInput() {
        UserInput result = validate.validateCommand("READ 50");
        assertEquals(50, result.getLBA());
    }

    @Test
    void testValidateReadCommandWithInvalidLBA() {
        UserInput result = validate.validateCommand("READ 150");
        assertEquals("INVALID COMMAND", result.getStatus());
    }

    @Test
    void testValidateWriteCommandWithValidInput() {
        UserInput result = validate.validateCommand("WRITE 50 0x123456GG");
        assertEquals("PASS", result.getStatus());
    }

    @Test
    void testValidateWriteCommandWithInvalidLBA() {
        UserInput result = validate.validateCommand("WRITE 150 0x12345678");
        assertEquals("INVALID COMMAND", result.getStatus());
    }

    @Test
    void testValidateWriteCommandWithInvalidValue() {
        UserInput result = validate.validateCommand("WRITE 50 0x123");
        assertEquals("INVALID COMMAND", result.getStatus());
    }

    @Test
    void testValidateFullWriteCommandWithValidInput() {
        UserInput result = validate.validateCommand("FULLWRITE 0x12345678");
        assertEquals("PASS", result.getStatus());
    }

    @Test
    void testValidateFullWriteCommandWithInvalidValue() {
        UserInput result = validate.validateCommand("FULLWRITE 0x123");
        assertEquals("INVALID COMMAND", result.getStatus());
    }

    @Test
    void testValidateLBAWithNonNumericInput() {
        assertFalse(validate.validateLBA("abc"));
    }

    @Test
    void testValidateValueWithInvalidPrefix() {
        assertFalse(validate.validateValue("12345678"));
    }

    @Test
    void testValidateValueWithInvalidLength() {
        assertFalse(validate.validateValue("0x123456"));
    }

    @Test
    void validateWriteComand(){
        String str = "write  3  0xAAAABBBB";
        Validate validate = new Validate();
        validate.validateCommand(str);
    }

    @Test
    void validateReadComand(){
        String str = "read  3";
        Validate validate = new Validate();
        validate.validateCommand(str);
    }

    @Test
    void validateFullWriteComand(){
        String str = "fullwrite  0x00000000";
        Validate validate = new Validate();
        validate.validateCommand(str);
    }

    @Test
    void validateFullReadComand(){
        String str = "fullread";
        Validate validate = new Validate();
        validate.validateCommand(str);
    }

    @Test
    void validateHelpComand(){
        String str = "help";
        validate.validateCommand(str);
    }

    @Test
    void doubleValidate(){
        String str = "write 3 0x123";
        UserInput ret = validate.validateCommand(str);
        assertEquals("INVALID COMMAND",ret.getStatus());

        str = "help";
        ret = validate.validateCommand(str);
        assertEquals("PASS",ret.getStatus());
    }

}