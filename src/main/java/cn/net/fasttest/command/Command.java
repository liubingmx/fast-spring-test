package cn.net.fasttest.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liubingmx@163.com
 * @createTime: 2024/01/07
 */
public class Command {

    private String name;

    private String description;

    private List<Option> options;


    public Command(String command, String description, List<Option> options) {
        this.name = command;
        this.description = description;
        this.options = options;
    }

    public Command(String name) {
        this.name = name;
    }

    public Command(String commandName, Option option) {
        this.name = commandName;
        this.options = new ArrayList<>();
        this.options.add(option);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public static class Option{

        private String args;

        private String description;

        public Option(String args) {
            this.args = args;
        }

        public String getArgs() {
            return args;
        }

        public void setArgs(String args) {
            this.args = args;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
