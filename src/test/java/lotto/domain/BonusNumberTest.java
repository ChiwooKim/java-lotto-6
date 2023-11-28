package lotto.domain;

import static lotto.util.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {

    private Lotto winningNumbers;

    @BeforeEach
    void beforeEach() {
        this.winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("보너스 번호가 로또 숫자 범위를 벗어난 경우")
    void validateRange() {
        assertThatThrownBy(() -> BonusNumber.createBonusNumber(0, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_OUT_OF_RANGE.getMessage());

        assertThatThrownBy(() -> BonusNumber.createBonusNumber(46, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_OUT_OF_RANGE.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복된 경우")
    void validateDuplication() {
        assertThatThrownBy(() -> BonusNumber.createBonusNumber(3, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("보너스 번호 존재 여부 확인")
    void isBonusNumber() {
        BonusNumber bonusNumber1 = BonusNumber.createBonusNumber(7, winningNumbers);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 7);

        assertThat(bonusNumber1.isBonusNumber(numbers)).isTrue();

        BonusNumber bonusNumber2 = BonusNumber.createBonusNumber(8, winningNumbers);
        assertThat(bonusNumber2.isBonusNumber(numbers)).isFalse();
    }
}