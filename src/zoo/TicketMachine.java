package zoo;

import dataStructures.CashCount;
import dataStructures.ICashCount;
import dataStructures.Money;
import dataStructures.Denom;

/**
 * A class representing the zoo's ticket machine. Responsible
 * for the entrance fee including giving out change.
 */
public class TicketMachine {

    private static final Money STANDARD_ENTRANCE_FEE = new Money(20);

    private Money entranceFee;
    private CashCount cashSupply;

    public TicketMachine() {
        entranceFee = STANDARD_ENTRANCE_FEE;
        cashSupply = new CashCount();

        // give the machine some starting cash:
        int i = 1;
        for (Denom denomination : Denom.values()) {
            i++;
            cashSupply.setNr(denomination, i);
        }
    }

    /**
     * Attempts to pay the entrance fee with the given cash.
     * @return the inserted cash if it's too little or no change
     * can be given / the change if it's too much / an empty
     * CashCount if it's exactly right
     */
    public ICashCount payEntranceFee(ICashCount cashInserted) {
        Money insertedAmount = new Money((CashCount) cashInserted);
        if (insertedAmount.compareTo(entranceFee) < 0) {
            System.out.println("Inserted money less than entrance fee.");
            return cashInserted;
        }

        cashSupply.add((CashCount) cashInserted);
        if (insertedAmount.compareTo(entranceFee) == 0) {
            return new CashCount();
        }

        return change(insertedAmount, cashInserted);
    }

    /**
     * Determines whether change can be given. If so, returns the
     * change. If not, returns the inserted cash.
     *
     * @param insertedAmount an amount of money that exceeds
     *                       the entrance fee
     * @param cashInserted the corresponding CashCount
     * @return the change or the inserted cash if giving change is impossible
     */
    private ICashCount change(Money insertedAmount, ICashCount cashInserted) {
        CashCount change = tryChange(insertedAmount);
        if (change == null) {
            System.out.println("Cannot give change.");
            cashSupply.remove((CashCount) cashInserted);
            return cashInserted;
        } else {
            cashSupply.remove(change);
            return change;
        }
    }

    /**
     * Calculates the change for a given payment of the
     * entrance fee.
     *
     * Specifically, it goes through the denominations in descending
     * order and always aims to give the highest number of notes/coins
     * possible (so the change comes in fewer notes/coins).
     * @param insertedAmount an amount of money that exceeds
     *                       the entrance fee
     * @return the change or null if giving change is impossible
     */
    private CashCount tryChange(Money insertedAmount) {
        Money changeAmount = insertedAmount.minus(entranceFee);
        CashCount change = new CashCount();
        for (Denom denomination : Denom.values()) {
            int nrAvailable = cashSupply.getNr(denomination);
            int nrPayable = changeAmount.divideBy(denomination);
            int nrChange = Math.min(nrAvailable, nrPayable);
            change.setNr(denomination, nrChange);
            changeAmount = changeAmount.minus(new Money(denomination, nrChange));
        }
        return changeAmount.isZero() ? change : null;
    }

    public void setEntranceFee(int pounds, int pence) throws IllegalArgumentException {
        if (pounds < 0 || pence < 0) {
            throw new IllegalArgumentException("Entrance fee cannot be negative.");
        }
        if (pence >= 100) {
            throw new IllegalArgumentException("Number of pence cannot exceed 99.");
        }
        entranceFee = new Money(pounds, pence);
    }

    public Money getEntranceFee() { // for testing
        return entranceFee;
    }

    public void setCashSupply(ICashCount cash) {
        cashSupply = (CashCount) cash;
    }

    public ICashCount getCashSupply() {
        return cashSupply;
    }

}
