package com.example.ecom.exception;

import com.example.ecom.exception.common.FieldConflictException;
import com.example.ecom.exception.common.FieldValidationException;
import com.example.ecom.exception.common.ResourceNotFoundException;
import com.example.ecom.exception.model.ExceptionDetails;
import com.example.ecom.interceptors.exception.HttpHeaderException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GeneralException {

    private static final String ERROR_MESSAGE_DATABASE_CONNECTION_NOT_AVAILABLE = "error.message.database.connection.not.available";
    private static final String ERROR_MESSAGE_HOST_NOT_REACHABLE = "error.message.host.not.reachable";
    private static final String ERROR_MESSAGE_INVALID_FORMAT_JSON = "error.message.invalid.format.json";
    private static final String ERROR_NO_FIELD_ERROR = "error.message.no.filed";
    private static final String ERROR_MESSAGE_INVALID_BAD_SQL_Grammar = "error.message.invalid.badSqlGrammar";
    private static final String ERROR_MESSAGE_INVALID_QUERY = "error.message.invalid.query.";
    private static final String VALUE = ".value";
    private static final String EXCEPTION_OCCURS_IN = "Exception occurs in ";

    @Autowired
    MessageSource messageSource;


    //--------------------------
    //Begin Common Exception
    //--------------------------
    @ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<ExceptionDetails> handleFieldValidationException(
            final FieldValidationException fieldValidationException) {

        final String message = getMessage(fieldValidationException.getMessage());
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(fieldValidationException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldConflictException.class)
    public ResponseEntity<ExceptionDetails> handleFieldConflictException(
            final FieldConflictException fieldConflictException) {

        final String message = getMessage(fieldConflictException.getMessage());
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.CONFLICT.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(fieldConflictException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(
            final ResourceNotFoundException resourceNotFoundException) {

        final String message = getMessage(resourceNotFoundException.getMessage());
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.NOT_FOUND.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(resourceNotFoundException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidStateException(
            final InvalidStateException invalidStateException) {

        final String message = getMessage(invalidStateException.getMessage());

        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.OK.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(invalidStateException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.OK);

    }

    //--------------------------
    //End Common Exception
    //--------------------------

    /**
     * <p>
     * Internal Server Exception
     * </p>
     */

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<ExceptionDetails> handleIncorrectResultSizeDataAccessException(
            final IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException) {

        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                incorrectResultSizeDataAccessException.getMessage(),
                HttpStatus.OK.toString(),
                DateHelper.getDatetimeStamp());

        printException(incorrectResultSizeDataAccessException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.OK);
    }

    @ExceptionHandler(SQLGrammarException.class)
    public ResponseEntity<ExceptionDetails> handleSQLGrammarException(
            final SQLGrammarException sqlGrammarException) {

        final String message = getMessage(sqlGrammarException.getMessage());
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                DateHelper.getDatetimeStamp());

        printException(sqlGrammarException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public ResponseEntity<ExceptionDetails> handleJDBCConnectionException(
            final JDBCConnectionException jdbcConnection) {

        final String message = getMessage(ERROR_MESSAGE_DATABASE_CONNECTION_NOT_AVAILABLE);
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                DateHelper.getDatetimeStamp());

        printException(jdbcConnection, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionDetails> handleMissingServletRequestParameterException(
            final MissingServletRequestParameterException missingParameterException) {

        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                missingParameterException.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(missingParameterException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpHeaderException.class)
    public ResponseEntity<ExceptionDetails> handleDateTimeParseException(
            final HttpHeaderException httpHeaderException) {

        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                httpHeaderException.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ExceptionDetails> handleDateTimeParseException(
            final DateTimeParseException dateTimeParseException) {

        final String message = getMessage(ERROR_MESSAGE_INVALID_FORMAT_JSON);
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(dateTimeParseException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> handleHttpMessageNotReadableException(
            final HttpMessageNotReadableException httpMessageNotReadableException) {

        Throwable mostSpecificCause = httpMessageNotReadableException.getMostSpecificCause();
        {
            final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                    mostSpecificCause.getMessage(),
                    HttpStatus.BAD_REQUEST.toString(),
                    DateHelper.getDatetimeStamp()
            );
            return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDetails> handleIllegalArgumentException(
            final IllegalArgumentException httpMessageNotReadableException) {
        final String message = getMessage(ERROR_MESSAGE_INVALID_FORMAT_JSON);
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(httpMessageNotReadableException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionDetails> handleHttpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {

        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                httpRequestMethodNotSupportedException.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp());

        printException(httpRequestMethodNotSupportedException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        final BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        final List<ObjectError> allErrors = bindingResult.getAllErrors();

        CollectionValidationUtils.notEmpty(
                allErrors,
                ERROR_NO_FIELD_ERROR
        );

        if (allErrors.size() > 1) {
            final List<ExceptionDetails> errorMessages = allErrors
                    .stream()
                    .map(this::getMessage)
                    .map(ExceptionDetails::of)
                    .collect(Collectors.toList());

            log.error(EXCEPTION_OCCURS_IN + errorMessages);
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        final ExceptionDetails details = allErrors
                .stream()
                .map(this::getMessage)
                .map(ExceptionDetails::of)
                .findFirst()
                .orElse(null);

        printException(methodArgumentNotValidException, details);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);

    }

    //FileSizeLimitExceededException
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionDetails> handleMaxUploadSizeExceededException
    (MaxUploadSizeExceededException e) {
        // throw new RuntimeException("Maximum upload size exceeded");
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp());

        printException(e, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

  /*  @ExceptionHandler(FileSizeLimitExceededException.class)
    public void handleMaxFileSizeLimitExceededException(FileSizeLimitExceededException e) {
        throw new RuntimeException("The field file exceeds its maximum permitted size of 2097152 bytes");
    }*/

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionDetails> HttpMediaTypeNotSupportedException(
            final HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {

        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                httpMediaTypeNotSupportedException.getMessage(),
                HttpStatus.NOT_FOUND.toString(),
                DateHelper.getDatetimeStamp());

        printException(httpMediaTypeNotSupportedException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ExceptionDetails> handleConnectException(
            final ConnectException connectException) {

        final String message = getMessage(ERROR_MESSAGE_HOST_NOT_REACHABLE);
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                DateHelper.getDatetimeStamp());

        printException(connectException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ExceptionDetails> handleUnknownHostException(
            final UnknownHostException unknownHostException) {

        final String message = getMessage(ERROR_MESSAGE_HOST_NOT_REACHABLE);
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(unknownHostException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {

        final String name = methodArgumentTypeMismatchException.getName();
        final String message = getMessage(ERROR_MESSAGE_INVALID_QUERY + name + VALUE);
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(methodArgumentTypeMismatchException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ExceptionDetails> handleBadSqlGrammarException(
            final BadSqlGrammarException badSqlGrammarException) {

        final String message = getMessage(ERROR_MESSAGE_INVALID_BAD_SQL_Grammar);
        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(badSqlGrammarException, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //--------------------------
    //Begin General Exception
    //--------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleException(
            final Exception exception) {

        final ExceptionDetails exceptionDetails = ExceptionDetails.createException(
                exception.toString(),
                HttpStatus.BAD_REQUEST.toString(),
                DateHelper.getDatetimeStamp()
        );

        printException(exception, exceptionDetails);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    //--------------------------
    //End General Exception
    //--------------------------
    private String getMessage(final String message) {

        final Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(message, null, locale);
        } catch (NoSuchMessageException e) {
            return message;
        }
    }

    private String getMessage(final ObjectError objectError) {

        final Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(objectError, locale);
    }

    private void printException(final Exception resourceCreationFailedException,
                                final ExceptionDetails exception) {
        log.error(EXCEPTION_OCCURS_IN + exception);
    }
}