package sharework.logintask.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

    private String errorMessage;
    private Integer errorCode;
}
