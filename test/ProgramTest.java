import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

public class ProgramTest {
    Robot robot;
    Program program;
    Logger logger;

    static class VisitorStub implements Visitor {
        private int stub = 0;

        @Override
        public void visitProgram(Program pProgram) {
            stub += 101;
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
        program = new Program(robot);
        program.addAction(new ComplexAction(robot, "grab move grab and compact",
                new GrabAction(robot),
                new CompactAction(robot),
                new MoveAction(robot, 20),
                new GrabAction(robot),
                new CompactAction(robot)));
        program.addAction(new EmptyAction(robot));
        logger = new Logger();
    }

    @Test
    void testAddAction() throws NoSuchFieldException, IllegalAccessException {
        program = new Program(robot);
        program.addAction(new GrabAction(robot));
        Field actions = program.getClass().getDeclaredField("actions");
        actions.setAccessible(true);
        assert (((List<Action>) actions.get(program)).size() == 1);
    }

    /**
     * tests for the correct end state of the robot
     */
    @Test
    void testExecute() {
        program.addObserver(logger);
        program.execute();
        assert (robot.getCompactorLevel() == 0);
        assert (robot.getGripperState() == Robot.GripperState.OPEN);
        assert (robot.getArmState() == Robot.ArmState.RETRACTED);
    }

    /**
     * tests for correct traversal and accept structure
     */
    @Test
    void testAccept() {
        VisitorStub visitorStub = new VisitorStub();
        program.accept(visitorStub);
        assert (visitorStub.getStub() == 100);
    }
}
