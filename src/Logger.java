import java.util.*;

/**
 * Specific implementation of a logging system that keeps logging statements
 */
public class Logger implements Observer {
    private final List<String> aLogs;

    /**
     * Create a new logging system
     */
    public Logger() {
        aLogs = new ArrayList<>();
    }

    /**
     * Receive information about the action execution
     *
     * @param pLog the information about the action
     * @pre pLog != null;
     */
    @Override
    public void notifyMe(String pLog) {
        assert pLog != null;

        aLogs.add(pLog);
    }

    @Override
    public String toString() {
        return Arrays.toString(aLogs.toArray());
    }
}
