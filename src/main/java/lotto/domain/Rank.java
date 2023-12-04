package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {

    //당첨 번호랑 매칭된 개수, 보너스 번호 존재 여부, 출력 메시지, 상금
    //NONE 당첨된 것이 아닌 로또를 칭한다.
    FIFTH(3, List.of(true, false), "3개 일치 (%,d원) - %d개%n", 5000),
    FOURTH(4, List.of(true, false), "4개 일치 (%,d원) - %d개%n", 50000),
    THIRD(5, List.of(false), "5개 일치 (%,d원) - %d개%n", 1500000),
    SECOND(5, List.of(true), "5개 일치, 보너스 볼 일치 (%,d원) - %d개%n", 30000000),
    FIRST(6, List.of(true, false), "6개 일치 (%,d원) - %d개%n", 2000000000),
    NONE(0, List.of(true, false), "", 0);

    private final int match;
    private final List<Boolean> bonus;
    private final String message;
    private final int winningAmount;

    Rank(int match, List<Boolean> bonus, String message, int winningAmount) {
        this.match = match;
        this.bonus = bonus;
        this.message = message;
        this.winningAmount = winningAmount;
    }

    public static Rank findRank(int matchNumber, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getMatch() == matchNumber)
                .filter(rank -> rank.getBonus().contains(bonus))
                .findAny().orElse(Rank.NONE);
    }

    public int getMatch() {
        return match;
    }

    public List<Boolean> getBonus() {
        return bonus;
    }

    public String getMessage() {
        return message;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
