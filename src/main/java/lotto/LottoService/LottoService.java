package lotto.LottoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.PurchaseHistory;
import lotto.domain.Rank;

public class LottoService {

    public PurchaseHistory purchaseLotto(int number) {
        return new PurchaseHistory(number);
    }

    public HashMap<Rank, Integer> checkLottoNumbers(PurchaseHistory purchaseHistory,
                                        Lotto winningNumbers, BonusNumber bonusNumber) {

        List<Rank> matchResult = getRanks(purchaseHistory, winningNumbers, bonusNumber);
        return getResult(matchResult);
    }

    public double getYield(PurchaseAmount purchaseAmount, HashMap<Rank, Integer> result) {
        return purchaseAmount.calculateYield(result);
    }

    private List<Rank> getRanks(PurchaseHistory purchaseHistory, Lotto winningNumbers, BonusNumber bonusNumber) {
        List<Rank> matchResult = new ArrayList<>();
        for (Lotto lotto : purchaseHistory.getPurchaseHistory()) {
            int matchNumber = lotto.matchWinningNumber(winningNumbers.getNumbers());
            boolean bonus = bonusNumber.isBonusNumber(lotto.getNumbers());
            matchResult.add(Rank.findRank(matchNumber, bonus));
        }
        return matchResult;
    }

    private HashMap<Rank, Integer> getResult(List<Rank> results) {
        HashMap<Rank, Integer> lottoResult = new HashMap<>();
        for (Rank result : results) {
            if (lottoResult.containsKey(result)) {
                lottoResult.put(result, lottoResult.get(result) + 1);
                continue;
            }
            lottoResult.put(result, 1);
        }
        return lottoResult;
    }
}
