/**
 * Represents something that is visitable to allow abstract operations on the visitable object
 * In this case, the nodes of a program which contains a structure of actions
 * Note that in this implementation, traversal is placed within the accept methods to enhance encapsulation
 *
 * @param <T>
 */
public interface Visitable<T extends Visitor> {
    /**
     * The method that allows the concrete visitor to perform the function on the object itself
     * Implement the traversal order in this method
     *
     * @param pVisitor the visitor that will perform computations
     * @pre pVisitor!=null;
     */
    void accept(T pVisitor);
}
