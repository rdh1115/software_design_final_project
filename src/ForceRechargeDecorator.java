/**
 * represents a decorator to be added to a critical action such that before its execution the robot
 * is forced to be recharged
 */
public class ForceRechargeDecorator extends Action {
    private final Action aAction;

    /**
     * Creates a new critical action
     *
     * @param pRobot  the robot to be acted on
     * @param pAction the action that needs to be critical
     * @pre pAction != null;
     */
    public ForceRechargeDecorator(Robot pRobot, Action pAction) {
        super(pRobot);

        assert pAction != null;
        aAction = pAction;
    }

    /**
     * Recharge the robot's battery before executing the critical action
     */
    @Override
    public synchronized final void execute() {
        Robot aRobot = super.getRobot();
        aRobot.rechargeBattery();

        aAction.execute();
    }

    /**
     * Accept the concrete visitor by passing it to the action that is decorated
     *
     * @param pVisitor the concrete visitor for computation related to the action
     */
    @Override
    public void accept(Visitor pVisitor) {
        aAction.accept(pVisitor);
    }

    @Override
    public String toString() {
        return "Force recharge of " + aAction.toString();
    }
}
