import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a complex action that is an aggregate of basic actions
 * The sequence of the basic actions are executed FIFO
 */
public class ComplexAction extends Action {
    private final List<BasicAction> actionsList;
    private final String aName;

    /**
     * Creates a new complex action that aggregates a sequence of basic actions
     * No modification of the aggregate is allowed
     *
     * @param pRobot   the robot that the actions will act on
     * @param pName    the name of the complex action
     * @param pActions the sequence of basic actions
     * @pre pActions != null && pName != null;
     */
    public ComplexAction(Robot pRobot, String pName, BasicAction... pActions) {
        super(pRobot);
        assert pActions != null;
        assert pName != null;

        actionsList = List.of(pActions);
        aName = pName;
    }

    /**
     * @return the list of basic actions related to this complex action
     */
    public List<BasicAction> getActions() {
        return Collections.unmodifiableList(actionsList);
    }

    /**
     * Execute each basic action FIFO
     */
    @Override
    public synchronized void execute() {

        for (BasicAction basicAction : actionsList) {
            basicAction.execute();
        }

    }

    /**
     * Visit the complex action first, then let each action aggregated in this complex action accept the visitor
     *
     * @param pVisitor the visitor that will perform computations
     */
    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitComplexAction(this);
        for (BasicAction action : actionsList) {
            action.accept(pVisitor);
        }
    }

    /**
     * Used for the logging method
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Complex action name: '" + aName + "' of these basic actions: ");
        stringBuilder.append(Arrays.toString(actionsList.toArray()));
        return stringBuilder.toString();
    }
}
