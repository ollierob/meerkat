package net.meerkat.numeric.exception;

public class DivideByZeroException extends ArithmeticException {

    public DivideByZeroException(final Object numerator, final Object denominator) {
        super(numerator + "/" + denominator);
    }

}
