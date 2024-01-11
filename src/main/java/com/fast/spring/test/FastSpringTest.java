package com.fast.spring.test;

import com.fast.spring.test.command.Command;
import com.fast.spring.test.command.CommandHandlerFactory;
import com.fast.spring.test.command.CommandParser;
import com.fast.spring.test.configuration.Configuration;
import com.fast.spring.test.exception.FastTestException;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultHighlighter;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class FastSpringTest {

    public static void run() {
        run(new Configuration.ConfigurationBuilder().build());
    }

    public static void run(Configuration configuration)  {

        LineReader lineReader = getLineReader();
        while (true) {
            try {
                String commandLine = lineReader.readLine(configuration.getPrompt()).trim();
                if (commandLine.isEmpty()) {
                    continue;
                }

                Command command = ((CommandParser)lineReader.getParser()).parseCommand(commandLine);
                CommandHandlerFactory.getCommandHandler(command.getName()).run(command);
            } catch (FastTestException e) {
                System.out.println("\n" + e.getMessage());
                continue;
            } catch (EndOfFileException e) {
                System.out.println("\nBye ~ ");
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error " + e.getMessage());
            }


        }
    }



    private static LineReader getLineReader() {
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .color(true)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return LineReaderBuilder.builder()
                .terminal(terminal)
                .history(new DefaultHistory())
                .highlighter(new DefaultHighlighter())
                .parser(new CommandParser())
                .build();
    }

}
