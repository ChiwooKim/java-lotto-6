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
        assertThatThrownBy(() -> new BonusNumber(0, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_OUT_OF_RANGE.getMessage());

        assertThatThrownBy(() -> new BonusNumber(46, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_OUT_OF_RANGE.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복된 경우")
    void validateDuplication() {
        assertThatThrownBy(() -> new BonusNumber(3, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE_NUMBER.getMessage());
    }
}