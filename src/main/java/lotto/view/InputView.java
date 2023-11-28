package lotto.view;

import static lotto.util.ErrorMessage.*;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public Integer readPurchaseAmount() {
        String amount = input();
        validateDigit(amount);
        return Integer.parseInt(amount);
    }

    public List<Integer> readWinningNumbers() {
        String winningNumbers = input();
        return Arrays.stream(winningNumbers.split(","))
                .peek(this::validateDigit)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public Integer readBonusNumber() {
        String bonusNumber = input();
        validateDigit(bonusNumber);
        return Integer.parseInt(bonusNumber);
    }

    private String input() {
        return Console.readLine();
    }

    private void validateDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INVALID_NUMERIC.getMessage());
        }
    }
}
