/**
 * represents an empty compactor action on a robot
 */
public final class EmptyAction extends BasicAction {

    /**
     * See super constructor
     *
     * @param pRobot the robot that is tied to this basic action
     */
    public EmptyAction(Robot pRobot) {
        super(pRobot);
    }

    /**
     * Empties the compactor if there is something in the compactor
     *
     * @throws IllegalStateException if the compactor is empty
     * @post compactor is empty
     */
    @Override
    void perform() {
        Robot aRobot = super.getRobot();
        if (aRobot.getCompactorLevel() > 0) aRobot.emptyCompactor();
        else {
            throw new IllegalStateException("Cannot empty an empty compactor");
        }
    }

    @Override
    public String toString() {
        return "Empty action";
    }
}
