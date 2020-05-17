package com.example.ecom.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(staticName = "createException")
@ToString
@Getter
public class ExceptionDetails {

    private final String message;
    private final String code;
    private final String timeStamp;


    public static ExceptionDetails of(final String exception) {
        return ExceptionDetails.createException(
                exception,
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );
    }


}
