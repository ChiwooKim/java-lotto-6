package lotto.domain;

import static lotto.util.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @ParameterizedTest
    @DisplayName("구매 금액이 범위를 벗어난 경우")
    @ValueSource(ints = {500, 120000})
    void validateRange(int amount) {
        //정상적인 경우
        assertThatCode(() -> new PurchaseAmount(10000)).doesNotThrowAnyException();

        //에러 발생
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_AMOUNT_RANGE.getMessage());
    }

    @Test
    @DisplayName("잔돈이 남는 경우")
    void validateUnit() {
        //정상적인 경우
        assertThatCode(() -> new PurchaseAmount(10000)).doesNotThrowAnyException();

        //에러 발생
        assertThatThrownBy(() -> new PurchaseAmount(1111))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_AMOUNT_UNIT.getMessage());
    }

    @Test
    @DisplayName("구입 개수 출력 확인")
    void getNumberOfPurchases() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(10000);

        assertThat(purchaseAmount.getNumberOfPurchases()).isEqualTo(10);
    }
}