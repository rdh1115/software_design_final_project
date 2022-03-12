/**
 * Represents an abstract robot action that is executable
 */
public abstract class Action implements Executable {
    private final Robot aRobot; //the robot of interest

    /**
     * Creates a new robot action to execute
     * Note that the robot is immutable
     *
     * @param pRobot the robot that will be acted on
     * @pre pRobot!=null;
     */
    public Action(Robot pRobot) {
        assert pRobot != null;

        aRobot = pRobot;
    }

    /**
     * @return the robot that is tied to this action
     */
    public Robot getRobot() {
        return aRobot;
    }

    public abstract String toString();

}
