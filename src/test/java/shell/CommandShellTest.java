package shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandShellTest {

    @Mock
    private Controller mockController;
    @Mock
    private InputProvider mockInputProvider;

    @Test
    void main_function_must_present() {
        try {
            Class<?> cls = Class.forName("shell.CommandShell");
            Method method = cls.getMethod("main", String[].class);

            int modifiers = method.getModifiers();
            assertTrue(Modifier.isPublic(modifiers), "main 메서드는 public이어야 합니다.");
            assertTrue(Modifier.isStatic(modifiers), "main 메서드는 static이어야 합니다.");
            assertEquals(method.getReturnType(), void.class, "main 메서드의 반환 타입은 void여야 합니다.");
        } catch (ClassNotFoundException e) {
            fail("클래스를 찾을 수 없습니다.");
        } catch (NoSuchMethodException e) {
            fail("main 메서드가 없습니다.");
        }
    }

    @Test
    void testRunMethodWithUserInput() {
        Controller mockController = mock(Controller.class);
        InputProvider mockInputProvider = mock(InputProvider.class);

        when(mockInputProvider.getInput()).thenReturn("test", "exit");

        CommandShell shell = new CommandShell(mockController, mockInputProvider);
        shell.run();

        verify(mockController, times(1)).receiveUserInputString("test");
    }

    @Test
    void send_to_controller() {
        CommandShell commandShell = new CommandShell(mockController, mockInputProvider);
        when(mockInputProvider.getInput()).thenReturn("test", "exit");

        commandShell.run();

        verify(mockController, times(1)).receiveUserInputString("test");
    }
}