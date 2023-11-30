package lotto.domain;

import static lotto.util.ErrorMessage.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MINIMUM_RANGE = 1;
    private static final int LOTTO_MAXIMUM_RANGE = 45;
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public int matchWinningNumber(List<Integer> winningNumbers) {
        int match = 0;
        for (Integer winningNumber : winningNumbers) {
            if (numbers.contains(winningNumber)) {
                match++;
            }
        }
        return match;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplications(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LOTTO_MINIMUM_RANGE || number > LOTTO_MAXIMUM_RANGE) {
                throw new IllegalArgumentException(INVALID_OUT_OF_RANGE.getMessage());
            }
        }
    }

    private void validateDuplications(List<Integer> numbers) {
        if (Set.copyOf(numbers).size() != Lotto_size) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
