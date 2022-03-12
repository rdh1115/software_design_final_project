import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

public class MoveActionTest {
    Robot robot;
    MoveAction moveAction;

    @BeforeEach
    void setup() {
        robot = new WallE();
        moveAction = new MoveAction(robot, 20);
    }

    /**
     * robot does not allow moving backwards
     */
    @Test
    void testConstructor() {
        try {
            moveAction = new MoveAction(robot, -20);
            fail();
        } catch (IllegalArgumentException e) {
            //pass
        }
    }

    @Test
    void testPerform() throws NoSuchFieldException, IllegalAccessException {
        Field armState = robot.getClass().getDeclaredField("armState");
        armState.setAccessible(true);

        robot.extendArm();
        moveAction.execute();
        assert (armState.get(robot) == Robot.ArmState.RETRACTED);
    }

    @Test
    void testGetDistance() {
        assert (moveAction.getDistance() == 20);
    }

    @Test
    void testToString() {
        assert (moveAction.toString().equalsIgnoreCase("Move Action for 20.0 meters"));
    }
}
