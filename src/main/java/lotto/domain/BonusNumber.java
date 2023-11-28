package lotto.domain;

import static lotto.util.ErrorMessage.*;


public class BonusNumber {

    private static final int LOTTO_MINIMUM_RANGE = 1;
    private static final int LOTTO_MAXIMUM_RANGE = 45;
    private final int bonusNumber;

    public BonusNumber(int bonusNumber, Lotto winningNumbers) {
        validate(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int number, Lotto winningNumbers) {
        validateRange(number);
        validateDuplication(number, winningNumbers);
    }

    private void validateRange(int number) {
        if (number < LOTTO_MINIMUM_RANGE || number > LOTTO_MAXIMUM_RANGE) {
            throw new IllegalArgumentException(INVALID_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateDuplication(int number, Lotto winningNumbers) {
        if (winningNumbers.getNumbers().contains(number)) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER.getMessage());
        }
    }
}
