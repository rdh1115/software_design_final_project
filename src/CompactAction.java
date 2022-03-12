/**
 * represents basic compacting action of a robot
 */
public final class CompactAction extends BasicAction {

    /**
     * See super constructor
     *
     * @param pRobot the robot that is tied to this basic action
     */
    public CompactAction(Robot pRobot) {
        super(pRobot);
    }

    /**
     * ensure the gripper holds an object, then compact the object
     *
     * @throws IllegalStateException if the compactor has greater than or equal to 10 items. this is not automatically done to
     *                               separate the basic actions (EmptyAction) or if the compactor is not holding an object
     * @post robot gripper is open
     */
    @Override
    void perform() {
        Robot aRobot = super.getRobot();

        //ensuring the preconditions are satisfied
        if (aRobot.getGripperState() != Robot.GripperState.HOLDING_OBJECT) {
            throw new IllegalStateException("Not holding an object, cannot compact");
        }
        if (aRobot.getCompactorLevel() >= 10) throw new IllegalStateException("Compactor full");

        aRobot.compact();

        //ensuring the post condition is satisfied in case of erroneous robot implementation
        if (aRobot.getGripperState() != Robot.GripperState.OPEN) aRobot.openGripper();
    }

    @Override
    public String toString() {
        return "Compact action";
    }
}
