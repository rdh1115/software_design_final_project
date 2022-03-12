import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Represents a program to be executed on a robot and logged
 * The specific actions are executed FIFO
 */
public class Program implements Executable, Observable {
    private final List<Action> actions;
    private final Robot aRobot;
    private final Set<Observer> aObservers;

    /**
     * Creates a new program that will execute a sequence of actions on a robot
     *
     * @param pRobot the robot to act on
     * @pre pRobot != null
     */
    public Program(Robot pRobot) {
        assert pRobot != null;
        aRobot = pRobot;
        actions = new LinkedList<>();//initialize
        aObservers = new HashSet<>();//initialize
    }

    /**
     * Add a new action to the program
     *
     * @param pAction
     */
    public void addAction(Action pAction) {
        assert pAction != null;
        actions.add(pAction);
    }

    /**
     * Execute the actions in a program FIFO
     * After successful execution of each action, notify observers
     * Note that the program is observable instead of the actions since there might be multiple programs with same actions
     */
    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute();
            notifyObservers(action + " performed, battery level is " + aRobot.getBatteryCharge());
        }
        notifyObservers("Finished all actions");
    }

    @Override
    public final void addObserver(Observer pObserver) {
        assert pObserver != null;
        aObservers.add(pObserver);
    }

    @Override
    public final void removeObserver(Observer pObserver) {
        assert pObserver != null;
        aObservers.remove(pObserver);
    }

    @Override
    public final void notifyObservers(String pString) {
        for (Observer aObserver : aObservers) {
            aObserver.notifyMe(pString);
        }
    }

    /**
     * @param pVisitor the visitor that will perform computations
     */
    @Override
    public void accept(Visitor pVisitor) {
        assert pVisitor != null;

        pVisitor.visitProgram(this);
        for (Action action : actions) {
            action.accept(pVisitor);
        }
    }
}
