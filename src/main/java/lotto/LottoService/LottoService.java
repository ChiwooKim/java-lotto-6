package lotto.LottoService;

import lotto.domain.PurchaseHistory;

public class LottoService {

    public PurchaseHistory getPurchaseHistory(int number) {
        return new PurchaseHistory(number);
    }
}
