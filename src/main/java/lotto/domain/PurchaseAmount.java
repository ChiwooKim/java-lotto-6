package lotto.domain;

import static lotto.util.ErrorMessage.*;

public class PurchaseAmount {

    public static final int REMAINDER = 0;
    public static final int AMOUNT_UNIT = 1000;
    public static final int INPUT_AMOUNT_MIN_LIMIT = 1000;
    public static final int INPUT_AMOUNT_MAX_LIMIT = 100000;
    private final int amount;

    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public Integer getNumberOfPurchases() {
        return amount / AMOUNT_UNIT;
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
