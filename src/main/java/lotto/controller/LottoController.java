package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void draw() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();
    }

    private PurchaseAmount inputPurchaseAmount() {
        outputView.printPurchaseAmountInputMessage();
        while (true) {
            try {
                Integer amount = inputView.readPurchaseAmount();
                return new PurchaseAmount(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
