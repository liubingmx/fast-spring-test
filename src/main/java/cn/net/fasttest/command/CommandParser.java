package cn.net.fasttest.command;

import org.jline.reader.impl.DefaultParser;

/**
 * @author liubingmx@163.com
 * @create 2024/01/11
 */
public class CommandParser extends DefaultParser {

    private static final String DEFAULT_COMMAND = "run";
    private static final String EMPTY_STR = "";

    public Command parseCommand(String line) {
        return new Command(getCommandStr(line), getOption(line));
    }

    Command.Option getOption(final String line) {
        String option = line.replace(getCommand(line), EMPTY_STR).trim();
        return new Command.Option(option);
    }

    public String getCommandStr(final String line) {
        String command = getCommand(line);
        if (!EMPTY_STR.equals(command.trim())) {
            return command;
        }
        return DEFAULT_COMMAND;
    }
}
