package net.meerkat.identifier.instrument.option;

public enum OptionDeliveryMonth {

    JANUARY('A', 'M'),
    FEBRUARY('B', 'N'),
    MARCH('C', 'O'),
    APRIL('D', 'P'),
    MAY('E', 'Q'),
    JUNE('F', 'R'),
    JULY('G', 'S'),
    AUGUST('H', 'T'),
    SEPTEMBER('I', 'U'),
    OCTOBER('J', 'V'),
    NOVEMBER('K', 'W'),
    DECEMBER('L', 'X');

    private final char call, put;

    OptionDeliveryMonth(char call, char put) {
        this.call = call;
        this.put = put;
    }

    public char call() {
        return call;
    }

    public char put() {
        return put;
    }

}
