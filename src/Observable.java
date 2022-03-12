/**
 * Represents something to be observed by a logger
 *
 * @param <T>
 */
public interface Observable<T extends Observer> {
    /**
     * Notify the logger of a state change
     *
     * @param pStirng the information to send to the oberver
     */
    void notifyObservers(String pStirng);

    /**
     * Add an observer interested in the state change of the object
     *
     * @param pObserver the observer to be added
     * @pre pObserver!=null;
     */
    void addObserver(T pObserver);

    /**
     * Removes an observer that was interested in the state change of the object
     *
     * @param pObserver the observer to be removed
     * @pre pObserver!=null;
     */
    void removeObserver(T pObserver);
}
