package cn.net.fasttest.event;

import cn.net.fasttest.command.Command;
import cn.net.fasttest.command.CommandParser;
import cn.net.fasttest.command.handler.HistoryCommandHandler;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultHighlighter;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author bing
 * @create 2024/01/23
 */
public class EventTest {

    @Test
    public void testEvent() throws IOException {
        Terminal terminal = TerminalBuilder.builder().build();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .history(new DefaultHistory())
                .highlighter(new DefaultHighlighter())
                .parser(new CommandParser())
                .build();
        HistoryCommandHandler listener = new HistoryCommandHandler();
        EventBus.addListener(listener);
        FastSpringTestEvent fastSpringTestEvent = new FastSpringTestEvent(EventEnum.STARTED, lineReader);
        EventBus.publishEvent(fastSpringTestEvent);
        listener.run(new Command("history", ""));
    }
}
