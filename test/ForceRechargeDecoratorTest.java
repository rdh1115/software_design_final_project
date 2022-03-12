import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ForceRechargeDecoratorTest {
    Action action;
    Robot robot;
    ForceRechargeDecorator forceRechargeDecorator;

    static class VisitorStub implements Visitor {
        private int stub = 0;

        @Override
        public void visitProgram(Program pProgram) {

        }

        @Override
        public void visitComplexAction(ComplexAction pComplexAction) {
            stub += pComplexAction.getActions().size();
        }

        @Override
        public void visitAction(Action pAction) {
            stub--;
        }

        public int getStub() {
            return stub;
        }
    }

    static class ActionStub extends Action {

        public ActionStub(Robot pRobot) {
            super(pRobot);
        }

        @Override
        public String toString() {
            return "stub";
        }

        @Override
        public void execute() {

        }

        @Override
        public void accept(Visitor pVisitor) {
            pVisitor.visitAction(this);
        }
    }

    @BeforeEach
    void setup() {
        robot = new WallE();
        action = new ActionStub(robot);
        forceRechargeDecorator = new ForceRechargeDecorator(robot, action);
    }

    /**
     * the battery at the end should be 100
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Test
    void testExecute() throws IllegalAccessException, NoSuchFieldException {
        Field batteryLevel = robot.getClass().getDeclaredField("charge");
        batteryLevel.setAccessible(true);
        batteryLevel.set(robot, 5);

        forceRechargeDecorator.execute();
        assert (robot.getBatteryCharge() == 100);
    }

    @Test
    void testAccept() {
        VisitorStub visitorStub = new VisitorStub();
        forceRechargeDecorator.accept(visitorStub);
        assert (visitorStub.getStub() == -1);
    }
}
