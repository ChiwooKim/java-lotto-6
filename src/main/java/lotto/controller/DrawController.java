package lotto.controller;

import java.util.HashMap;
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

    public DrawController() {
        this.purchaseLotto = new PurchaseLotto();
        this.issueLotto = new IssueLotto();
    }

    public void draw() {
        inputPurchaseAmount();
        issueLotto.issue(purchaseLotto.getNumberOfPurchases());
        List<Integer> winningNumbers = inputWinningNumber().getNumbers();
        int bonusNumber = inputBonusNumber(winningNumbers);

        List<LottoRankings> matchResult = MatchLotto.createMatchLotto()
                .matchLotto(winningNumbers, bonusNumber, issueLotto.getLottoPurchaseHistory());
        HashMap<LottoRankings, Integer> lottoResult = LottoResult.createLottoResult()
                .checkResult(matchResult);
        OutputView.printResultMessage(lottoResult);

        checkYield(lottoResult);
    }

    private void checkYield(HashMap<LottoRankings, Integer> lottoResult) {
        String yield = Yield.createYield()
                .calculateYield(purchaseLotto.getPurchaseAmount(), lottoResult);
        OutputView.printYieldMessage(yield);
    }

    private int inputBonusNumber(List<Integer> winningNumbers) {
        OutputView.printBonusNumberInputMessage();
        BonusNumber bonusNumber = new BonusNumber();
        try {
            return bonusNumber.inputBonusNumber(InputView.input(), winningNumbers);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return bonusNumber.inputBonusNumber(InputView.input(), winningNumbers);
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
