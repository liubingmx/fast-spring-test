package cn.net.fasttest.configuration;

import cn.net.fasttest.utils.FontColorUtil;

/**
 * @author bing
 * create 2024/01/07
 */
public class Configuration {

    /**
     *  cli prompt
     */
    private String prompt;

    private String promptColor;


    public Configuration(ConfigurationBuilder builder) {
        this.prompt = builder.getPrompt();
        this.promptColor = builder.getPromptColor();
    }

    public String getPrompt() {
        return FontColorUtil.format(prompt, promptColor);
    }

    public static class ConfigurationBuilder {
        private String prompt = "\033[1;34mFast-Spring-Test ->\033[0m";

        private String promptColor = FontColorUtil.BULE;

        private ConfigurationBuilder() {
        }

        public static ConfigurationBuilder builder() {
            return new ConfigurationBuilder();
        }

        public ConfigurationBuilder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public ConfigurationBuilder promptColor(String promptColor) {
            this.promptColor = promptColor;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }

        public String getPrompt() {
            return prompt;
        }

        public String getPromptColor() {
            return promptColor;
        }
    }


}
