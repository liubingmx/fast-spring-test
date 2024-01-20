package cn.net.fasttest.command;

/**
 * @author bing
 * @create 2024/01/07
 */
public interface CommandHandler {

    /**
     * @return command
     */
    Command getCommand();

    /**
     *  run the command
     * @param command args
     */
    void run(Command command);
}
