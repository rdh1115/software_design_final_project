import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

public class ReleaseActionTest {
    Robot robot;
    ReleaseAction releaseAction;

    @BeforeEach
    void setup() {
        robot = new WallE();
        releaseAction = new ReleaseAction(robot);
    }

    /**
     * similar to grabAction, tests for precondition enforcement
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    void testPerform() throws NoSuchFieldException, IllegalAccessException {
        Field armState = robot.getClass().getDeclaredField("armState");
        armState.setAccessible(true);
        armState.set(robot, Robot.ArmState.EXTENDED);

        Field gripperState = robot.getClass().getDeclaredField("gripperState");
        gripperState.setAccessible(true);
        gripperState.set(robot, Robot.GripperState.EMPTY);

        try {
            releaseAction.execute();
            fail();
        } catch (IllegalStateException e) {
            gripperState.set(robot, Robot.GripperState.OPEN);
            try {
                releaseAction.execute();
                fail();
            } catch (IllegalStateException e1) {
                gripperState.set(robot, Robot.GripperState.HOLDING_OBJECT);
                releaseAction.execute();
                assert (gripperState.get(robot) == Robot.GripperState.OPEN);
                assert (armState.get(robot) == Robot.ArmState.RETRACTED);
            }
        }
    }

    @Test
    void testToString() {
        assert (releaseAction.toString().equalsIgnoreCase("Release Action"));
    }

}
