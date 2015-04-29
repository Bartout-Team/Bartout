package ch.zhaw.bartout.service.deepcopy;

/**
 * Created by serge on 26.04.2015.
 */

/**
 * Gives the ability to make a deep copy
 * The class and every dependent class have to implement the Serializable interface
 * @param <E>
 */
public interface Copyable<E> {
    public E copy();
}
