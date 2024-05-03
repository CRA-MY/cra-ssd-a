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
        // Controller와 InputProvider 모킹
        Controller mockController = mock(Controller.class);
        InputProvider mockInputProvider = mock(InputProvider.class);

        // mockInputProvider가 "test"와 "exit" 문자열을 순서대로 반환하도록 설정
        when(mockInputProvider.getInput()).thenReturn("test", "exit");

        // CommandShell 객체 생성 및 run 메소드 실행
        CommandShell shell = new CommandShell(mockController, mockInputProvider);
        shell.run();

        // getUserInput 메소드가 "test" 문자열로 한 번 호출되었는지 검증
        verify(mockController, times(1)).getUserInput("test");
    }

    @Test
    void send_to_controller() {
        CommandShell commandShell = new CommandShell(mockController, mockInputProvider);
        // mockInputProvider가 "test"와 "exit"를 순차적으로 반환하도록 설정
        when(mockInputProvider.getInput()).thenReturn("test", "exit");

        // CommandShell의 run 메소드 실행
        commandShell.run();

        // mockController의 getUserInput 메소드가 "test" 문자열로 정확히 한 번 호출되었는지 검증
        verify(mockController, times(1)).getUserInput("test");
    }
}