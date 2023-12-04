package lotto.domain;

import static lotto.util.ErrorMessage.*;

import java.util.List;


public class BonusNumber {

    private static final int LOTTO_MINIMUM_RANGE = 1;
    private static final int LOTTO_MAXIMUM_RANGE = 45;
    private final int bonusNumber;

    private BonusNumber(int bonusNumber, Lotto winningNumbers) {
        validate(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber createBonusNumber(int bonusNumber, Lotto winningNumbers) {
        return new BonusNumber(bonusNumber, winningNumbers);
    }

    public boolean isBonusNumber(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
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
