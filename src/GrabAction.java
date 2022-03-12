/**
 * represents basic grabbing action of a robot
 */
public final class GrabAction extends BasicAction {

    /**
     * See super constructor
     *
     * @param pRobot the robot that is tied to this basic action
     */
    public GrabAction(Robot pRobot) {
        super(pRobot);
    }

    /**
     * First make sure the gripper is open if it isn't holding an object
     * Extend the robot's arm if it isn't already extended
     * Close the robot's gripper and retract the robot's arm
     *
     * @throws IllegalStateException if the robot is already holding an object in its gripper
     * @post robot arm is retracted and gripper is closed
     */
    @Override
    void perform() {
        Robot aRobot = super.getRobot();

        //enforce the robot's gripper is open if it isn't holding an object
        if (aRobot.getGripperState() == Robot.GripperState.HOLDING_OBJECT) {
            throw new IllegalStateException("Already holding an object, cannot grab another one");
        } else if (aRobot.getGripperState() == Robot.GripperState.EMPTY) {
            if (aRobot.getArmState() == Robot.ArmState.EXTENDED) {
                aRobot.retractArm();
                aRobot.openGripper();
            } else {
                aRobot.openGripper();
            }
        }

        //extend the arm
        if (aRobot.getArmState() == Robot.ArmState.RETRACTED) aRobot.extendArm();

        //close gripper (if statement just in case concrete robot implementation is incorrect)
        if (aRobot.getGripperState() == Robot.GripperState.OPEN) aRobot.closeGripper();


        //retract arm (if statement just in case)
        if (aRobot.getArmState() == Robot.ArmState.EXTENDED) aRobot.retractArm();

    }

    @Override
    public String toString() {
        return "Grab action";
    }
}
