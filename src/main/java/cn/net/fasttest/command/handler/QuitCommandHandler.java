package cn.net.fasttest.command.handler;

import cn.net.fasttest.command.Command;
import cn.net.fasttest.command.CommandHandler;
import org.jline.reader.EndOfFileException;

/**
 * @author bing
 * @create 2024/03/07
 */
public class QuitCommandHandler implements CommandHandler {
    @Override
    public Command getCommand() {
        return new Command("quit", "quit.");
    }

    @Override
    public void run(Command command) {
        throw new EndOfFileException();
    }
}
