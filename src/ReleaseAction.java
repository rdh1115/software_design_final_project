/**
 * represents basic releasing action of a robot
 */
public final class ReleaseAction extends BasicAction {

    /**
     * See super constructor
     *
     * @param pRobot the robot that is tied to this basic action
     */
    public ReleaseAction(Robot pRobot) {
        super(pRobot);
    }

    /**
     * Enforce the robot arm is retracted, then release an object if the robot is holding an object
     *
     * @throws IllegalStateException if the robot is not holding an object
     * @post robot arm is retracted, gripper is open
     */
    @Override
    void perform() {
        Robot aRobot = super.getRobot();

        //enforce holding object, throws error to separate grab and release
        if (aRobot.getGripperState() != Robot.GripperState.HOLDING_OBJECT) {
            throw new IllegalStateException("Not holding an object to be released");
        }

        if (aRobot.getArmState() == Robot.ArmState.EXTENDED) aRobot.retractArm();
        //if statement just in case implementation of retractArm() is erroneous
        if (aRobot.getGripperState() == Robot.GripperState.HOLDING_OBJECT) aRobot.openGripper();
    }

    @Override
    public String toString() {
        return "Release action";
    }
}
