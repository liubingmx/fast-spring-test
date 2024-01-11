package com.fast.spring.test.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author liubingmx@163.com
 * @create 2024/01/11
 */
public class CommandParserTest {

    @Test
    public void test() {
        String line = "run com.fast.spring.test.command.CommandParserTest.test";
        Command command = new CommandParser().parseCommand(line);
        Assertions.assertEquals("run", command.getName());

        line = "com.fast.spring.test.command.CommandParserTest.test";
        command = new CommandParser().parseCommand(line);
        Assertions.assertEquals("run", command.getName());

        line = "1";
        command = new CommandParser().parseCommand(line);
        Assertions.assertEquals("run", command.getName());
    }
}
