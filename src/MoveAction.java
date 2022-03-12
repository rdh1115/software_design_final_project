/**
 * Basic move action for robots
 */
public final class MoveAction extends BasicAction {

    private final double aDistance;

    /**
     * Moves the robot forward.
     *
     * @param pDistance the distance to travel, in meters.
     * @throws IllegalArgumentException if distance is negative since the robot can only move forward
     * @pre pDistance >= 0
     */
    public MoveAction(Robot pRobot, double pDistance) {
        super(pRobot);

        if (pDistance < 0) {
            throw new IllegalArgumentException("Cannot directly move robots backwards, enter positive distances");
        }
        aDistance = pDistance;
    }

    /**
     * @return the distance the move action is encoding
     */
    public double getDistance() {
        return aDistance;
    }

    /**
     * Retract the robot's arm if it isn't retracted, and move the robot by the specified distance
     *
     * @post arm retracted
     */
    @Override
    void perform() {
        Robot aRobot = super.getRobot();

        //enforce the arm is retracted
        if (aRobot.getArmState() == Robot.ArmState.EXTENDED) aRobot.retractArm();

        aRobot.moveRobot(aDistance);
    }

    @Override
    public String toString() {
        return "Move action for " + this.aDistance + " meters";
    }
}
