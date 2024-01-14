package com.fast.spring.test.runner;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class TestRunResult {

    private String uniqueId;

    private String state;
    private String displayName;

    private Throwable throwable;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * Status of executing a single test or container.
     */
    public enum state {

        /**
         * Indicates that the execution of a test or container was
         * <em>successful</em>.
         */
        SUCCESSFUL,

        /**
         * Indicates that the execution of a test or container was
         * <em>aborted</em> (started but not finished).
         */
        ABORTED,

        /**
         * Indicates that the execution of a test or container <em>failed</em>.
         */
        FAILED;

    }
}
