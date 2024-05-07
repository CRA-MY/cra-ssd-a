package shell;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shell.dto.UserInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;

class ControllerTest {
    private Service service;
    private Controller controller;
    private Validate validate;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        service = mock(Service.class);
        validate = mock(Validate.class);
        controller = new Controller(service);
        controller.validate = validate;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void sendServiceShouldInvokeReadOnReadCommand() {
        UserInput userInput = new UserInput("read", 1, null);

        controller.sendService(userInput);

        verify(service, times(1)).read(userInput.getLBA());
    }

    @Test
    public void sendServiceShouldInvokeWriteOnWriteCommand() {
        UserInput userInput = new UserInput("write", 1, "0xABCDFFF");

        controller.sendService(userInput);

        verify(service, times(1)).write(userInput.getLBA(), userInput.getValue());
    }

    @Test
    public void sendServiceShouldInvokeHelpOnHelpCommand() {
        UserInput userInput = new UserInput("help", -1, null);

        controller.sendService(userInput);

        verify(service, times(1)).help();
    }

    @Test
    public void sendServiceShouldInvokeFullwriteOnFullwriteCommand() {
        UserInput userInput = new UserInput("fullwrite", -1, "0xABCDFFF");

        controller.sendService(userInput);

        verify(service, times(1)).fullwrite(userInput.getValue());
    }

    @Test
    void testReceiveUserInputStringWithValidCommand() {
        UserInput validUserInput = new UserInput("READ", 1, "value", "VALID");
        when(validate.validateCommand("read 1")).thenReturn(validUserInput);

        controller.receiveUserInputString("read 1");

        verify(service, times(1)).read(1);
        assertNotEquals("INVALID COMMAND\n", outContent.toString());
    }

    @Test
    void testReceiveUserInputStringWithInvalidCommand() {
        when(validate.validateCommand("invalid command")).thenThrow(new IllegalArgumentException("INVALID COMMAND"));

        controller.receiveUserInputString("invalid command");

        verify(service, never()).read(anyInt());
        assertEquals("INVALID COMMAND\r\n", outContent.toString());
    }
}