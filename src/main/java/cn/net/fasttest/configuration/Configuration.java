package cn.net.fasttest.configuration;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class Configuration {

    /**
     *  cli prompt
     */
    private String prompt = "\033[1;34mFast-Spring-Test ->\033[0m";
    private String commandSeparator = " ";

    public Configuration(ConfigurationBuilder builder) {
        this.prompt = builder.getPrompt();
    }

    public String getPrompt() {
        return prompt;
    }

    public String getCommandSeparator() {
        return commandSeparator;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public static class ConfigurationBuilder {
        private String prompt = "\033[1;34mFast-Spring-Test ->\033[0m";

        public static ConfigurationBuilder builder() {
            return new ConfigurationBuilder();
        }

        public ConfigurationBuilder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }

        public String getPrompt() {
            return prompt;
        }
    }


}
