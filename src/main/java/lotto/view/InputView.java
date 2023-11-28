package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public Integer readPurchaseAmount() {
        String amount = input();
        return Integer.parseInt(amount);
    }

    public List<String> readWinningNumbers() {
        String winningNumbers = input();
        return Arrays.stream(winningNumbers.split(","))
                .collect(Collectors.toList());
    }

    public Integer readBonusNumber() {
        String bonusNumber = input();
        return Integer.parseInt(bonusNumber);
    }

    private String input() {
        return Console.readLine();
    }
}
