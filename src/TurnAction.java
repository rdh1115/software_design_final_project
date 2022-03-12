/**
 * Basic turn 90˚ action for robots
 */
public final class TurnAction extends BasicAction {
    private final Direction aDirection;

    /**
     * Turns the robot by 90˚ to the right or left.
     *
     * @param pDirection the rotation direction, counterclockwise or clockwise
     * @pre arm is retracted
     */
    public TurnAction(Robot pRobot, Direction pDirection) {
        super(pRobot);

        assert pDirection != null;
        aDirection = pDirection;
    }

    /**
     * @return the direction associated with this turn action
     */
    public Direction getDirection() {
        return aDirection;
    }

    /**
     * Retract the robot's arm if it isn't retracted, then turn the robot in the specified direction
     *
     * @post arm retracted
     */
    @Override
    public void perform() {
        Robot aRobot = super.getRobot();
        if (aRobot.getArmState() == Robot.ArmState.EXTENDED) aRobot.retractArm();

        if (aDirection == Direction.COUNTERCLOCKWISE) aRobot.turnRobot(-90);
        else if (aDirection == Direction.CLOCKWISE) aRobot.turnRobot(90);
    }

    @Override
    public String toString() {
        return "Turn action to " + this.aDirection;
    }

    public static enum Direction {
        COUNTERCLOCKWISE,
        CLOCKWISE,
    }

}
