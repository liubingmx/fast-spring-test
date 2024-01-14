package com.fast.spring.test.command.handler;

import com.fast.spring.test.command.Command;
import com.fast.spring.test.command.CommandHandler;
import com.fast.spring.test.event.EventEnum;
import com.fast.spring.test.event.FastSpringTestEvent;
import com.fast.spring.test.event.FastSpringTestListener;
import org.jline.reader.History;
import org.jline.reader.LineReader;

import java.util.ListIterator;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class HistoryCommandHandler implements CommandHandler, FastSpringTestListener {

    History defaultHistory;

    @Override
    public Command getCommand() {
        return new Command("history");
    }

    @Override
    public void run(Command command) {
        ListIterator<History.Entry> iterator = defaultHistory.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public void listen(FastSpringTestEvent event) {
        if (!EventEnum.STARTED.equals(event.getEvent())) {
            return;
        }
        this.defaultHistory = ((LineReader)event.getSource()).getHistory();
    }
}
