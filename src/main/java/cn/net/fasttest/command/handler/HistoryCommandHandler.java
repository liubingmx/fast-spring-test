package cn.net.fasttest.command.handler;

import cn.net.fasttest.command.Command;
import cn.net.fasttest.command.CommandHandler;
import cn.net.fasttest.event.EventEnum;
import cn.net.fasttest.event.FastSpringTestEvent;
import cn.net.fasttest.event.FastSpringTestListener;
import cn.net.fasttest.event.Subscribe;
import org.jline.reader.History;
import org.jline.reader.LineReader;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class HistoryCommandHandler implements CommandHandler, FastSpringTestListener {

    History defaultHistory;

    @Override
    public Command getCommand() {
        return new Command("history", "Show the most recently executed command");
    }

    @Override
    public void run(Command command) {
        for (History.Entry entry : defaultHistory) {
            System.out.println(entry);
        }
    }

    @Override
    @Subscribe(EventEnum.STARTED)
    public void listen(FastSpringTestEvent event) {
        this.defaultHistory = ((LineReader)event.getSource()).getHistory();
    }
}
