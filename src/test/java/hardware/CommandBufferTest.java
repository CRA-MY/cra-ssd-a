package hardware;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CommandBufferTest {

    @BeforeEach
    public void setUp() throws IOException {
        SSDFileWriter writer = new SSDFileWriter();
        writer.initFile("storage/buffer.txt");
    }

    @Test
    public void test_recontruction_case1() {
        CommandBuffer buffer = new CommandBuffer();

        try {
            buffer.writeBuffer("W 20 0xABCDABCD");
            buffer.writeBuffer("W 21 0x12341234");
            buffer.writeBuffer("W 20 0xEEEEFFFF");
        } catch (IOException e) {
        }
        buffer.recontruction();

    }

    @Test
    public void test_recontruction_case2() {
        CommandBuffer buffer = new CommandBuffer();

        try {
            buffer.writeBuffer("W 20 0xABCDABCD");
            buffer.writeBuffer("W 21 0x12341234");
            buffer.writeBuffer("E 18 5");
        } catch (IOException e) {
        }
        buffer.recontruction();
    }

    @Test
    public void test_recontruction_case3() {
        CommandBuffer buffer = new CommandBuffer();

        try {
            buffer.writeBuffer("E 10 2");
            buffer.writeBuffer("E 12 3");
        } catch (IOException e) {
        }
        buffer.recontruction();
    }

    @Test
    public void test_recontruction_case4() {
        CommandBuffer buffer = new CommandBuffer();

        try {
            buffer.writeBuffer("E 12 3");
            buffer.writeBuffer("E 10 2");
        } catch (IOException e) {
        }
        buffer.recontruction();
    }
}