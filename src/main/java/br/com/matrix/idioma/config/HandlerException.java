package br.com.matrix.idioma.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

	private static final String PAGE_ILLEGAL_ARGUMENT = "ILLEGAL ARGUMENT";
	private static final String PAGE_OBJECT_REGISTERED = "OBJECT ALREADY REGISTERED";

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<Object> handlerResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {
		ExceptionDetails error = new ExceptionDetails();
		error.setDetail(exception.getMessage());
		error.setDevMessage(exception.getClass().getName());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(getLocalDateTimeNow());
		error.setTitle(PAGE_NOT_FOUND_LOG_CATEGORY);

		return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = { ResourceIllegalArgumentException.class })
	public ResponseEntity<Object> handlerResourceIllegalArgumentException(ResourceIllegalArgumentException exception,
			WebRequest request) {
		ExceptionDetails error = new ExceptionDetails();
		error.setDetail(exception.getMessage());
		error.setDevMessage(exception.getClass().getName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(getLocalDateTimeNow());
		error.setTitle(PAGE_ILLEGAL_ARGUMENT);

		return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { ResourceObjectRegisteredException.class })
	public ResponseEntity<Object> handlerResourceObjectRegisteredException(ResourceObjectRegisteredException exception,
			WebRequest request) {
		ExceptionDetails error = new ExceptionDetails();
		error.setDetail(exception.getMessage());
		error.setDevMessage(exception.getClass().getName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(getLocalDateTimeNow());
		error.setTitle(PAGE_OBJECT_REGISTERED);

		return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException rn, WebRequest request) {

		ExceptionDetails error = new ExceptionDetails();
		error.setDetail(rn.getMessage());
		error.setDevMessage(rn.getClass().getName());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(getLocalDateTimeNow());
		error.setTitle("Entrada");
		return handleExceptionInternal(rn, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException rn,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionDetails error = new ExceptionDetails();
		error.setDetail(rn.getMessage());
		error.setDevMessage(rn.getClass().getName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(getLocalDateTimeNow());
		error.setTitle("Entrada invalida");
		return handleExceptionInternal(rn, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException rn,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionDetails error = new ExceptionDetails();
		error.setDetail(rn.getMessage());
		error.setDevMessage(rn.getClass().getName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(getLocalDateTimeNow());
		error.setTitle("Caracter inesperado");
		return handleExceptionInternal(rn, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private String getLocalDateTimeNow() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
				.withLocale(new Locale("pt", "br"));
		return now.format(formatter);
	}

}
