package shell;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shell.dto.UserInput;

import static org.mockito.Mockito.mock;

class ControllerTest {
    private Service service;
    private Controller controller;

    @BeforeEach
    public void setUp() {
        service = mock(Service.class);
        controller = new Controller(service);
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
    public void sendServiceShouldInvokeFullreadOnFullreadCommand() {
        UserInput userInput = new UserInput("fullread", -1, null);

        controller.sendService(userInput);

        verify(service, times(1)).fullread();
    }
}