import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

public class ComplexActionTest {
    Robot robot;
    ComplexAction complexAction;

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

    @BeforeEach
    void setup() {
        robot = new WallE();
        complexAction = new ComplexAction(robot, "grab move grab and compact",
                new GrabAction(robot),
                new CompactAction(robot),
                new MoveAction(robot, 20),
                new GrabAction(robot),
                new CompactAction(robot));
    }


    @Test
    void testGetActions() throws NoSuchFieldException, IllegalAccessException {
        try {
            complexAction.getActions().remove(0);
            fail();
        } catch (UnsupportedOperationException e) {
            Field actions = complexAction.getClass().getDeclaredField("actionsList");
            actions.setAccessible(true);

            assert (complexAction.getActions().equals(actions.get(complexAction)));
        }
    }

    /**
     * test for the final state of the robot
     */
    @Test
    void testExecute() {
        complexAction.execute();
        assert (robot.getCompactorLevel() == 2);
        assert (robot.getGripperState() == Robot.GripperState.OPEN);
        assert (robot.getArmState() == Robot.ArmState.RETRACTED);
    }


    @Test
    void testAccept() {
        VisitorStub visitorStub = new VisitorStub();
        complexAction.accept(visitorStub);
        assert (visitorStub.getStub() == 0);
    }

    @Test
    void testToString() {
        assert (complexAction.toString().equalsIgnoreCase("Complex action name: 'grab move grab and compact' of these basic actions: [Grab action, Compact action, Move action for 20.0 meters, Grab action, Compact action]"));
    }
}
