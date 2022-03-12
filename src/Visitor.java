/**
 * Represents a function that performs computations on a program for a robot
 * Implement the methods to traverse the program nodes
 * Note that traversal is already implemented in accept() of Visitable, so DO NOT implement traversal
 */
public interface Visitor {
    /**
     * Compute things related to a program
     * Usually empty implementation
     *
     * @param pProgram the program of computational interest
     */
    void visitProgram(Program pProgram);

    /**
     * Compute things related to a complex action which aggregates a sequence of basic actions
     * Note that traversal will visit each basic action within the complex action
     * Do not traverse into the basic actions
     *
     * @param pComplexAction the complex action of computational interest
     */
    void visitComplexAction(ComplexAction pComplexAction);

    /**
     * Compute things related to a specific action, the leaves of the program
     * Usually, this is the method that should be implemented for computation
     *
     * @param pAction the action of computational interest
     */
    void visitAction(Action pAction);
}
