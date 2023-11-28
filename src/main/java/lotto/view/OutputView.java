package lotto.view;

import java.util.HashMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {

    public static final String PURCHASE_AMOUNT_INPUT = "구입금액을 입력해 주세요.";
    public static final String NUMBER_OF_PURCHASE = "%n%d개를 구매했습니다.%n";
    public static final String WINNING_NUMBERS_INPUT = "%n당첨 번호를 입력해 주세요.%n";
    public static final String BONUS_NUMBER_INPUT = "%n보너스 번호를 입력해 주세요.%n";
    public static final String WINNING_STATISTIC = "%n당첨 통계%n---%n";
    public static final String YIELD = "총 수익률은 %.1f%%입니다.%n";
    public static final int NOTHING = 0;

    public void printPurchaseAmountInputMessage() {
        System.out.println(PURCHASE_AMOUNT_INPUT);
    }

    public void printNumberOfPurchase(int number) {
        System.out.printf(NUMBER_OF_PURCHASE, number);
    }

    public void printPurchaseHistory(List<Lotto> purchaseHistory) {
        for (Lotto lotto : purchaseHistory) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningNumbersInputMessage() {
        System.out.printf(WINNING_NUMBERS_INPUT);
    }

    public void printBonusNumberInputMessage() {
        System.out.printf(BONUS_NUMBER_INPUT);
    }

    public void printWinningStatistic(HashMap<Rank, Integer> lottoResult) {
        System.out.printf(WINNING_STATISTIC);
        printResult(lottoResult);
    }

    public void printYield(double yield) {
        System.out.printf(YIELD, yield);
    }

    private void printResult(HashMap<Rank, Integer> lottoResult) {
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            if (lottoResult.get(rank) != null) {
                System.out.printf(rank.getMessage(), rank.getWinningAmount(), lottoResult.get(rank));
                continue;
            }
            System.out.printf(String.format(rank.getMessage(), rank.getWinningAmount(), NOTHING));
        }
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
