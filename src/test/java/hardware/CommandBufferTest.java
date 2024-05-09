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
    public void test_recontruction_case1() throws IOException {
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
    public void test_recontruction_case2() throws IOException {
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
    public void test_recontruction_case3() throws IOException {

        // E 10 2 > start_index: 10, end_index: 12
        // E 12 3 > > current_start: 12, current_end: 15
        // => E 10 5 > 10~15
        // E 15 3 > start_index: 15, end_index: 18
        // E 12 3 > current_start: 12, current_end: 15
        // => E 12 6 > 12~18

        // 포함될 때
        // E 8 10 > start_index: 8, end_index: 18
        // E 10 3 > current_start: 10, current_end: 13
        // => E 8 10 > 8~18
        // 포함될 때
        // E 10 3 > start_index: 10, end_index: 13
        // E 8 10 > current_start: 8, current_end: 18
        // => E 8 10 > 8~18

        CommandBuffer buffer = new CommandBuffer();

        try {
            buffer.writeBuffer("E 10 2");
            buffer.writeBuffer("E 12 3");
        } catch (IOException e) {
        }
        buffer.recontruction();
    }

    @Test
    public void test_recontruction_case4() throws IOException {
        CommandBuffer buffer = new CommandBuffer();

        try {
            buffer.writeBuffer("E 12 3");
            buffer.writeBuffer("E 10 2");
        } catch (IOException e) {
        }
        buffer.recontruction();
    }
}