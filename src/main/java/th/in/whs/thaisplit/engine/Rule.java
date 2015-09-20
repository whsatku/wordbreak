package th.in.whs.thaisplit.engine;

public interface Rule<E, T> {
    boolean match(E input);
    T activate(E input);
}
