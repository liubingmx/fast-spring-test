package cn.net.fasttest.runner;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class TestRunResult {

    private String uniqueId;

    private String state;
    private String displayName;
    private long cost;
    private long started;
    private long succeeded;
    private long failed;
    private long skipped;

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(long succeeded) {
        this.succeeded = succeeded;
    }

    public long getFailed() {
        return failed;
    }

    public void setFailed(long failed) {
        this.failed = failed;
    }

    public long getSkipped() {
        return skipped;
    }

    public void setSkipped(long skipped) {
        this.skipped = skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
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
