package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.util.RandomNumberGenerator;

public class PurchaseHistory {

    private final List<Lotto> purchaseHistory;

    public PurchaseHistory(int number) {
        this.purchaseHistory = new ArrayList<>();
        addLotto(number);
    }

    private void addLotto(int number) {
        for (int i = 0; i < number; i++) {
            purchaseHistory.add(new Lotto(RandomNumberGenerator.generateNumbers()));
        }
    }
}
