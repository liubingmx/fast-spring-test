package cn.net.fasttest.command;

/**
 * @author: liubingmx@163.com
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
