package shell;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class CommendShellTest {

    @Test
    void main_function_must_present() {
        try {
            Class<?> cls = Class.forName("shell.CommendShell");
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
    void input_while_going_around() {
        String input = "ssd W 3 0xAAAABBBB";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        CommendShell commendShell = new CommendShell();

        String result = commendShell.getInput();
        assertEquals(input, result);
    }
}