package flow.flowreport.api.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final int errorCode;

    /**
     * API에서 발생한 오류코드는 상세화하지 않고 8888으로 하드코딩 처리함
     */
    public ApiException(String errorMessage)
    {
        super(errorMessage);
        this.errorCode = 8888;
    }
}
