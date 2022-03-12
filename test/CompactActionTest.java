import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

public class CompactActionTest {
    Robot robot;
    CompactAction compactAction;

    @BeforeEach
    void setup() {
        robot = new WallE();
        compactAction = new CompactAction(robot);
    }

    /**
     * check for errors that are thrown if preconditions aren't satisfied
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    void testPerform() throws NoSuchFieldException, IllegalAccessException {
        Field compactedItems = robot.getClass().getDeclaredField("compactedItems");
        compactedItems.setAccessible(true);
        compactedItems.set(robot, 10);

        Field gripperState = robot.getClass().getDeclaredField("gripperState");
        gripperState.setAccessible(true);
        try {
            compactAction.execute();
            fail();
        } catch (IllegalStateException e) {
            compactedItems.set(robot, 1);
            gripperState.set(robot, Robot.GripperState.OPEN);
            try {
                compactAction.execute();
                fail();
            } catch (IllegalStateException e1) {
                gripperState.set(robot, Robot.GripperState.EMPTY);
                try {
                    compactAction.execute();
                    fail();
                } catch (IllegalStateException e2) {
                    gripperState.set(robot, Robot.GripperState.HOLDING_OBJECT);
                    compactAction.execute();
                    assert (robot.getCompactorLevel() == 2);
                }
            }
        }
    }

    @Test
    void testToString() {
        assert (compactAction.toString().equalsIgnoreCase("Compact Action"));
    }
}
