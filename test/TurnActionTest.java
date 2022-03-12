import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class TurnActionTest {
    Robot robot;
    TurnAction turnAction;

    @BeforeEach
    void setup() {
        robot = new WallE();
        turnAction = new TurnAction(robot, TurnAction.Direction.CLOCKWISE);
    }

    @Test
    void testPerform() throws NoSuchFieldException, IllegalAccessException {
        Field armState = robot.getClass().getDeclaredField("armState");
        armState.setAccessible(true);

        robot.extendArm();
        turnAction.execute();
        assert (armState.get(robot) == Robot.ArmState.RETRACTED);
    }

    @Test
    void testGetDirection() {
        assert (turnAction.getDirection() != TurnAction.Direction.COUNTERCLOCKWISE);
    }

    @Test
    void testToString() {
        assert (turnAction.toString().equalsIgnoreCase("Turn Action to CLOCKWISE"));
    }
}
