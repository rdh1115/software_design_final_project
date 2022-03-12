/**
 * Represents something that is executable for a robot
 */
public interface Executable extends Visitable {
    /**
     * Execute a specific action on a robot
     */
    public void execute();
}
