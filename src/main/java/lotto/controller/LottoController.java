package lotto.controller;

import java.util.HashMap;
import java.util.List;
import lotto.LottoService.LottoService;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.PurchaseHistory;
import lotto.domain.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = new LottoService();
    }

    public void draw() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();
        PurchaseHistory purchaseHistory = getPurchaseHistory(purchaseAmount);
        Lotto winningNumbers = inputWinningNumbers();
        BonusNumber bonusNumber = inputBonusNumber(winningNumbers);
        HashMap<Rank, Integer> result = getLottoResult(purchaseHistory, winningNumbers, bonusNumber);
        findYield(purchaseAmount, result);
    }

    private void findYield(PurchaseAmount purchaseAmount, HashMap<Rank, Integer> result) {
        double yield = lottoService.getYield(purchaseAmount, result);
        outputView.printYield(yield);
    }

    private HashMap<Rank, Integer> getLottoResult(PurchaseHistory purchaseHistory,
                                                  Lotto winningNumbers, BonusNumber bonusNumber) {
        HashMap<Rank, Integer> result = lottoService
                .checkLottoNumbers(purchaseHistory, winningNumbers, bonusNumber);
        outputView.printWinningStatistic(result);
        return result;
    }

    private PurchaseHistory getPurchaseHistory(PurchaseAmount purchaseAmount) {
        outputView.printNumberOfPurchase(purchaseAmount.getNumberOfPurchases());
        PurchaseHistory purchaseHistory = lottoService
                .purchaseLotto(purchaseAmount.getNumberOfPurchases());
        outputView.printPurchaseHistory(purchaseHistory.getPurchaseHistory());
        return purchaseHistory;
    }

    private PurchaseAmount inputPurchaseAmount() {
        outputView.printPurchaseAmountInputMessage();
        while (true) {
            try {
                Integer amount = inputView.readPurchaseAmount();
                return PurchaseAmount.createPurchaseAmount(amount);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private Lotto inputWinningNumbers() {
        outputView.printWinningNumbersInputMessage();
        while (true) {
            try {
                List<Integer> winningNumbers = inputView.readWinningNumbers();
                return new Lotto(winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private BonusNumber inputBonusNumber(Lotto winningNumbers) {
        outputView.printBonusNumberInputMessage();
        while (true) {
            try {
                Integer bonusNumber = inputView.readBonusNumber();
                return new BonusNumber(bonusNumber, winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
