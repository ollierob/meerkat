package net.ollie.goat.data;

/**
 * @author Ollie
 */
public class KeyPair<L, R> {

    private final L left;
    private final R right;

    public KeyPair(final L a, final R b) {
        this.left = a;
        this.right = b;
    }

    public L left() {
        return left;
    }

    public R right() {
        return right;
    }

}
