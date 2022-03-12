import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class BasicActionTest {
    BasicAction basicAction;
    Robot robot;

    static class BasicActionStub extends BasicAction {

        public BasicActionStub(Robot pRobot) {
            super(pRobot);
        }

        @Override
        public String toString() {
            return "BasicActionStub";
        }

        @Override
        void perform() {
            super.getRobot().openGripper();
        }
    }

    static class VisitorStub implements Visitor {

        private String print;

        @Override
        public void visitProgram(Program pProgram) {

        }

        @Override
        public void visitComplexAction(ComplexAction pComplexAction) {

        }

        @Override
        public void visitAction(Action pAction) {
            print = pAction.toString();
        }
    }

    @BeforeEach
    void setup() {
        robot = new WallE();
        basicAction = new BasicActionStub(robot);
    }

    /**
     * Make sure the following protocol is implemented
     * First check the state of the battery
     * If the charge of the battery is <= 5 units, then recharge the battery
     * Perform the action and update the battery level
     */
    @Test
    void testExecute() throws NoSuchFieldException, IllegalAccessException {
        Field batteryLevel = robot.getClass().getDeclaredField("charge");
        batteryLevel.setAccessible(true);
        batteryLevel.set(robot, 5);

        basicAction.execute();
        assert (robot.getBatteryCharge() >= 95);
        assert (robot.getGripperState() == Robot.GripperState.OPEN);
    }

    /**
     * test visitor stub to confirm correctness
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    void testAccept() throws NoSuchFieldException, IllegalAccessException {
        Visitor visitor = new VisitorStub();
        basicAction.accept(visitor);
        Field string = visitor.getClass().getDeclaredField("print");
        string.setAccessible(true);

        assert (string.get(visitor).equals("BasicActionStub"));
    }
}
