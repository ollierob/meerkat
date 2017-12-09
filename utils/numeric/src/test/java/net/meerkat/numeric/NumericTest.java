package net.meerkat.numeric;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class NumericTest {

    protected abstract Numeric<?> valueOf(long l);

    @Test
    public void testIsZero() {
        assertTrue(valueOf(0).isZero());
    }

}