import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing abstract action
 */
public class ActionTest {
    Action action;
    Robot robot;


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

        }
    }

    @BeforeEach
    void setup() {
        robot = new WallE();
        action = new ActionStub(robot);
    }

    @Test
    void testGetRobot() {
        Robot robot2 = new WallE();
        assert (robot2 != action.getRobot());
    }
}
