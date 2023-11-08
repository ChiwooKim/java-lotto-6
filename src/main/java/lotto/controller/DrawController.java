package lotto.controller;

import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.IssueLotto;
import lotto.model.Lotto;
import lotto.model.LottoRankings;
import lotto.model.LottoResult;
import lotto.model.MatchLotto;
import lotto.model.PurchaseLotto;
import lotto.model.Yield;
import lotto.view.InputView;
import lotto.view.OutputView;

public class DrawController {

    private final PurchaseLotto purchaseLotto;
    private final IssueLotto issueLotto;
    private final BonusNumber bonusNumber;
    private final MatchLotto matchLotto;
    private final LottoResult lottoResult;
    private final Yield yield;

    public DrawController() {
        this.purchaseLotto = new PurchaseLotto();
        this.issueLotto = new IssueLotto();
        this.bonusNumber = new BonusNumber();
        this.matchLotto = new MatchLotto();
        this.lottoResult = new LottoResult();
        this.yield = new Yield();
    }

    public void draw() {
        inputPurchaseAmount();
        issueLotto.issue(purchaseLotto.getNumberOfPurchases());
        List<Integer> winningNumbers = inputWinningNumber().getNumbers();
        inputBonusNumber(winningNumbers);

        matchLotto.matchLotto(winningNumbers, bonusNumber.getBonusNumber(), issueLotto.getLottoPurchaseHistory());
        lottoResult.checkResult(matchLotto.getMatchResult());

        OutputView.printResultMessage(lottoResult.getLottoResult());

        checkYield();
    }

    private void checkYield() {
        long totalPrizeMoney = 0;
        for (LottoRankings rank : lottoResult.getLottoResult().keySet()) {
            totalPrizeMoney += rank.getWinningAmount() * lottoResult.getLottoResult().get(rank);
        }
        System.out.println(totalPrizeMoney);
        yield.calculateYield(totalPrizeMoney, purchaseLotto.getPurchaseAmount());
        OutputView.printYieldMessage(yield.getYield());
    }

    private void inputBonusNumber(List<Integer> winningNumbers) {
        OutputView.printBonusNumberInputMessage();
        try {
            bonusNumber.inputBonusNumber(InputView.input(), winningNumbers);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            bonusNumber.inputBonusNumber(InputView.input(), winningNumbers);
        }
    }

    private Lotto inputWinningNumber() {
        OutputView.printWinningNumberInputMessage();
        try {
            return Lotto.createWinningNumbers(InputView.input());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return Lotto.createWinningNumbers(InputView.input());
        }
    }

    private void inputPurchaseAmount() {
        OutputView.printPurchaseAmountInputMessage();
        try {
            purchaseLotto.purchase(InputView.input());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            purchaseLotto.purchase(InputView.input());
        }
    }
}
