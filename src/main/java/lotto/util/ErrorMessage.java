package lotto.util;

public enum ErrorMessage {
    INVALID_NUMERIC("숫자만 입력 가능합니다."),
    INVALID_AMOUNT_RANGE("최소 1,000원 최대 100,000원까지 구입이 가능합니다."),
    INVALID_AMOUNT_UNIT("1,000원 단위로 입력해 주세요.");

    private static final String ERROR_MESSAGE = "[ERROR] %s";
    private final String message;

    ErrorMessage(String message) {
        this.message = String.format(ERROR_MESSAGE, message);
    }

    public String getMessage() {
        return message;
    }
}
