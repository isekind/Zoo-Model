package dataStructures;

/**
 * An enum representing denominations
 */
public enum Denom {

    POUND_20(2000), POUND_10(1000),
    POUND_5(500), POUND_2(200), POUND_1(100),
    PENCE_50(50), PENCE_20(20), PENCE_10(10);

    /**
     * Each denomination has a value which is it's monetary value
     * in pence (to avoid floats/doubles).
     */
    private final int value;

    Denom(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
