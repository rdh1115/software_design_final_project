import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

public class GrabActionTest {
    Robot robot;
    GrabAction grabAction;

    @BeforeEach
    void setup() {
        robot = new WallE();
        grabAction = new GrabAction(robot);
    }

    /**
     * similar to releaseTest
     * test for enforcement of the preconditions to prevent robot damage
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
        gripperState.set(robot, Robot.GripperState.HOLDING_OBJECT);

        try {
            grabAction.execute();
            fail();
        } catch (IllegalStateException e) {
            gripperState.set(robot, Robot.GripperState.EMPTY);
            grabAction.execute();
            assert (gripperState.get(robot) == Robot.GripperState.HOLDING_OBJECT);
            assert (armState.get(robot) == Robot.ArmState.RETRACTED);
        }
    }

    @Test
    void testToString() {
        assert (grabAction.toString().equalsIgnoreCase("Grab Action"));
    }

}
