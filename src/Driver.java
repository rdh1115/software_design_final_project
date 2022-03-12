/**
 * the stub driver class that exercises the main scenarios of WALL-E action loaders
 */
public class Driver {
    public static void main(String[] args) {
        Robot WallE = new WallE();
        Program program = new Program(WallE);

        /*
        initializing logging system
         */
        Logger log = new Logger();
        program.addObserver(log);

        /*
        add all types of implemented actions to the program
         */
        program.addAction(
                new ForceRechargeDecorator(WallE,
                        new ComplexAction(WallE, "grab move grab and compact",
                                new GrabAction(WallE),
                                new CompactAction(WallE),
                                new MoveAction(WallE, 20),
                                new GrabAction(WallE),
                                new CompactAction(WallE))));
        program.addAction(new EmptyAction(WallE));

        /*
        count the number of compacted objects in the program without executing
         */
        Visitor countCompact = new Visitor() {
            private int totalCompact;

            @Override
            public void visitProgram(Program pProgram) {
            }

            @Override
            public void visitComplexAction(ComplexAction pComplexAction) {
            }

            @Override
            public void visitAction(Action pAction) {
                if (pAction instanceof CompactAction) totalCompact++;
            }
        };

        /*
        count the total distance travelled without executing
         */
        Visitor countDistance = new Visitor() {
            private int totalDistance;

            @Override
            public void visitProgram(Program pProgram) {
            }

            @Override
            public void visitComplexAction(ComplexAction pComplexAction) {
            }

            @Override
            public void visitAction(Action pAction) {
                if (pAction instanceof MoveAction) totalDistance += ((MoveAction) pAction).getDistance();
            }
        };

        //accept the computations
        program.accept(countCompact);program.accept(countCompact);

        program.execute();
    }
}
