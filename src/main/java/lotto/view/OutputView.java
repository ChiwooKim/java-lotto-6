package lotto.view;

import java.util.List;

public class OutputView {

    public static final String PURCHASE_AMOUNT_INPUT = "구입금액을 입력해 주세요.";
    public static final String NUMBER_OF_PURCHASE = "개를 구매했습니다.";
    public static final String WINNING_NUMBERS_INPUT = "당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_INPUT = "보너스 번호를 입력해 주세요.";
    public static final String WINNING_STATISTIC = "당첨 통계";
    public static final String LINE = "---";
    public static final String YIELD = "총 수익률은 %s%%입니다.";

    public void printPurchaseAmountInputMessage() {
        System.out.println(PURCHASE_AMOUNT_INPUT);
    }

    public static void printNumberOfPurchaseMessage(int number) {
        System.out.println(number + NUMBER_OF_PURCHASE);
    }

    public static void printIssueLottoMessage(List<Integer> numbers) {
        System.out.println(numbers);
    }

    public static void printWinningNumbersInputMessage() {
        System.out.println(WINNING_NUMBERS_INPUT);
    }

    public static void printBonusNumberInputMessage() {
        System.out.println(BONUS_NUMBER_INPUT);
    }
}
