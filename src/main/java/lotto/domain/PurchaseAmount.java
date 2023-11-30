package lotto.domain;

import static lotto.util.ErrorMessage.*;

import java.util.HashMap;

public class PurchaseAmount {

    private static final int REMAINDER = 0;
    private static final int AMOUNT_UNIT = 1000;
    private static final int INPUT_AMOUNT_MIN_LIMIT = 1000;
    private static final int INPUT_AMOUNT_MAX_LIMIT = 100000;
    private static final int CONVERT_PERCENT = 100;

    private final int amount;

    private PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static PurchaseAmount createPurchaseAmount(int amount) {
        return new PurchaseAmount(amount);
    }

    public Integer getNumberOfPurchases() {
        return amount / AMOUNT_UNIT;
    }

    public double calculateYield(HashMap<Rank, Integer> result) {
        long totalPrizeMoney = 0;
        for (Rank rank : result.keySet()) {
            totalPrizeMoney += (long) rank.getWinningAmount() * result.get(rank);
        }
        return  (totalPrizeMoney * CONVERT_PERCENT) / (double) amount;
    }

    private void validate(int amount) {
        validateAmountRange(amount);
        validateAmountUnit(amount);
    }

    private void validateAmountRange(int amount) {
        if (amount < INPUT_AMOUNT_MIN_LIMIT || amount > INPUT_AMOUNT_MAX_LIMIT) {
            throw new IllegalArgumentException(INVALID_AMOUNT_RANGE.getMessage());
        }
    }

    private void validateAmountUnit(int amount) {
        if (amount % AMOUNT_UNIT > REMAINDER) {
            throw new IllegalArgumentException(INVALID_AMOUNT_UNIT.getMessage());
        }
    }
}
