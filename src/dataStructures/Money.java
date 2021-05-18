package dataStructures;

/**
 * A class representing amounts of money.
 */
public class Money implements Comparable<Money> {

    /**
     * Internally, the amount of money is stored as pence
     * (to avoid floats/doubles).
     */
    int value;

    public Money() {
        this(0);
    }

    public Money(int pounds) {
        value = pounds * 100;
    }

    public Money(int pounds, int pence) {
        value = pounds * 100 + pence;
    }

    public Money(Denom denomination, int nr) {
        value = denomination.getValue() * nr;
    }

    /**
     * Converts a CashCount into an amount of money.
     */
    public Money(CashCount cash) {
        value = 0;
        for (Denom denomination : Denom.values()) {
            value += denomination.getValue() * cash.getNr(denomination);
        }
    }

    /**
     * Checks whether this is less, more or equal to the given Money.
     * @return -1 if this is less than that, 1 if it is more, 0 if it is equal
     */
    public int compareTo(Money that) {
        return Integer.compare(value, that.value);
    }

    /**
     * @return A Money instance containing the difference
     * between this and that
     */
    public Money minus(Money that) {
        Money result = new Money();
        result.value = value - that.value;
        return result;
    }

    /**
     * @return how many notes/coins of the given denomination
     * "fit" into this amount of money
     */
    public int divideBy(Denom denomination) {
        return value / denomination.getValue();
    }

    public boolean isZero() {
        return value == 0;
    }

    public String toString() { // for testing
        return "" + (value / 100.0);
    }

    public boolean equals(Money that) { // for testing
        return value == that.value;
    }

}













