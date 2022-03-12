/**
 * Represents an abstract basic robot action using
 * Uses template design pattern to ensure protocol is followed
 * See currently implemented compact, move, turn, empty, release, grab
 */
public abstract class BasicAction extends Action {

    /**
     * See super constructor
     *
     * @param pRobot the robot that is tied to this basic action
     */
    public BasicAction(Robot pRobot) {
        super(pRobot);
    }

    /**
     * Ensures all basic actions follow the protocol using template design pattern
     * To implement a new basic action, override perform
     *
     * @pre the pre conditions of the robot interface
     */
    public synchronized final void execute() {
        Robot aRobot = super.getRobot();
        int batteryLevel = aRobot.getBatteryCharge();
        if (batteryLevel <= 5) aRobot.rechargeBattery();
        perform();
        aRobot.updateBatteryLevel();
    }

    /**
     * Override this method to implement a new basic action of a sequence of motor movements
     */
    abstract void perform();

    /**
     * Accept the concrete visitor
     *
     * @param pVisitor the concrete visitor for computation related to the action
     */
    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitAction(this);
    }
}
