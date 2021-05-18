package dataStructures;

import java.util.HashMap;

/**
 * A class representing an amount of money in 20-, 10-, and 5-pound
 * notes; 2- and 1-pound coins; and 50-, 20-, and 10-pence coins.
 */
public class CashCount implements ICashCount {

    private HashMap<Denom, Integer> cash = new HashMap<>();

    public CashCount() {
        for (Denom denomination : Denom.values()) {
            cash.put(denomination, 0);
        }
    }

    /**
     * Adds the contents of another CashCount to this one. (Adding money)
     */
    public void add(CashCount newCash) {
        for (Denom denomination : Denom.values()) {
            cash.replace(denomination, cash.get(denomination) + newCash.cash.get(denomination));
        }
    }

    /**
     * Removes the contents of another CashCount from this one. (Removing money)
     */
    public void remove(CashCount payableCash) throws IllegalArgumentException {
        for (Denom denomination : Denom.values()) {
            if (cash.get(denomination) < payableCash.cash.get(denomination)) {
                throw new IllegalArgumentException("Cannot remove more notes "
                        + "/ coins from this CashCount than are present.");
            }
            cash.replace(denomination, cash.get(denomination) - payableCash.cash.get(denomination));
        }
    }

    /**
     * Sets the number of notes/coins of a given denomination to a
     * given number.
     */
    public void setNr(Denom denomination, int nr) {
        validatePositive(nr);
        cash.replace(denomination, nr);
    }

    public void setNrNotes_20pounds(int noteCount) {
        validatePositive(noteCount);
        cash.replace(Denom.POUND_20, noteCount);
    }

    public void setNrNotes_10pounds(int noteCount) {
        validatePositive(noteCount);
        cash.replace(Denom.POUND_10, noteCount);
    }

    public void setNrNotes_5pounds(int noteCount) {
        validatePositive(noteCount);
        cash.replace(Denom.POUND_5, noteCount);
    }

    public void setNrCoins_2pounds(int coinCount) {
        validatePositive(coinCount);
        cash.replace(Denom.POUND_2, coinCount);
    }

    public void setNrCoins_1pound(int coinCount) {
        validatePositive(coinCount);
        cash.replace(Denom.POUND_1, coinCount);
    }

    public void setNrCoins_50p(int coinCount) {
        validatePositive(coinCount);
        cash.replace(Denom.PENCE_50, coinCount);
    }

    public void setNrCoins_20p(int coinCount) {
        validatePositive(coinCount);
        cash.replace(Denom.PENCE_20, coinCount);
    }

    public void setNrCoins_10p(int coinCount) {
        validatePositive(coinCount);
        cash.replace(Denom.PENCE_10, coinCount);
    }

    private void validatePositive(int nr) throws IllegalArgumentException {
        if (nr < 0) {
            throw new IllegalArgumentException("The number of notes "
                    + "/ coins cannot be negative.");
        }
    }

    /**
     * @return The number of notes/coins of the given denomination.
     */
    public int getNr(Denom denomination) {
        return cash.get(denomination);
    }

    public int getNrNotes_20pounds() {
        return cash.get(Denom.POUND_20);
    }

    public int getNrNotes_10pounds() {
        return cash.get(Denom.POUND_10);
    }

    public int getNrNotes_5pounds() {
        return cash.get(Denom.POUND_5);
    }

    public int getNrCoins_2pounds() {
        return cash.get(Denom.POUND_2);
    }

    public int getNrCoins_1pound() {
        return cash.get(Denom.POUND_1);
    }

    public int getNrCoins_50p() {
        return cash.get(Denom.PENCE_50);
    }

    public int getNrCoins_20p() {
        return cash.get(Denom.PENCE_20);
    }

    public int getNrCoins_10p() {
        return cash.get(Denom.PENCE_10);
    }

    public boolean equals(CashCount that) { // for testing
        return cash.equals(that.cash);
    }

}
