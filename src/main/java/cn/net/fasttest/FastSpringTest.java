package cn.net.fasttest;

import cn.net.fasttest.command.CommandHandlerFactory;
import cn.net.fasttest.command.CommandParser;
import cn.net.fasttest.configuration.Configuration;
import cn.net.fasttest.event.EventBus;
import cn.net.fasttest.event.EventEnum;
import cn.net.fasttest.event.FastSpringTestEvent;
import cn.net.fasttest.exception.FastTestException;
import cn.net.fasttest.command.Command;
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
        EventBus.publishEvent(new FastSpringTestEvent(EventEnum.STARTED, lineReader));
        while (true) {
            try {
                String commandLine = lineReader.readLine(configuration.getPrompt()).trim();
                if (commandLine.isEmpty()) {
                    continue;
                }
                Command command = ((CommandParser)lineReader.getParser()).parseCommand(commandLine);
                CommandHandlerFactory.getCommandHandler(command.getName()).run(command);
                EventBus.publishEvent(new FastSpringTestEvent(EventEnum.EXECUTE_COMMAND, command));
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
