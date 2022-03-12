import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

public class EmptyActionTest {
    Robot robot;
    EmptyAction emptyAction;

    @BeforeEach
    void setup() {
        robot = new WallE();
        emptyAction = new EmptyAction(robot);
    }

    /**
     * test for error that will be thrown if precondition is not satisfied
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    void testPerform() throws NoSuchFieldException, IllegalAccessException {
        Field compactedItems = robot.getClass().getDeclaredField("compactedItems");
        compactedItems.setAccessible(true);
        compactedItems.set(robot, 0);

        try {
            emptyAction.execute();
            fail();
        } catch (IllegalStateException e) {
            compactedItems.set(robot, 1);
            emptyAction.execute();
            assert (robot.getCompactorLevel() == 0);
        }
    }

    @Test
    void testToString() {
        assert (emptyAction.toString().equalsIgnoreCase("Empty Action"));
    }
}
