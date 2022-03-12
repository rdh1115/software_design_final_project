/**
 * Represents a logging system that will be notified if an action within a program is executed
 */
public interface Observer {
    /**
     * Receive the state change of an observable
     * Since this is a logging system, the state change is the observable being executed
     *
     * @param pLog String that contains information related to the executed action and the battery level
     */
    void notifyMe(String pLog);
}
