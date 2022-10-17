package net.meerkat.instrument.equity;

public record StockClass(String name) {

    public static final StockClass CLASS_A = new StockClass("A");
    public static final StockClass CLASS_B = new StockClass("B");
    public static final StockClass CLASS_C = new StockClass("C");

}
