package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.util.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @Test
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    void createLottoByOverSize() {
        assertThatThrownBy(() -> Lotto.createLotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_SIZE.getMessage());
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> Lotto.createLotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("로또 번호가 1 ~ 45 범위를 벗어난 경우")
    void validateNumberRange() {
        assertThatThrownBy(() -> Lotto.createLotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_OUT_OF_RANGE.getMessage());

        assertThatThrownBy(() -> Lotto.createLotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_OUT_OF_RANGE.getMessage());
    }

    @Test
    @DisplayName("정상적인 입력값인 경우")
    void passCase() {
        assertThatCode(() -> Lotto.createLotto(List.of(11, 22, 33, 44, 45, 6))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨 번호 매칭 확인")
    void matchNumber() {
        Lotto purchaseLotto = Lotto.createLotto(List.of(1, 2, 3, 4, 5, 6));
        int number = purchaseLotto.matchWinningNumber(List.of(4, 5, 6, 7, 8, 9));

        assertThat(number).isEqualTo(3);
    }
}