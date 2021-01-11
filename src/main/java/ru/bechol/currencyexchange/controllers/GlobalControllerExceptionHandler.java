package ru.bechol.currencyexchange.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.bechol.currencyexchange.response.ErrorResponse;
import ru.bechol.currencyexchange.service.exception.*;

/**
 * Class GlobalControllerExceptionHandler.
 * Handling global app exceptions.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Slf4j
@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalControllerExceptionHandler {

	/**
	 * Method handleNotReadableException.
	 * Handling HttpMessageNotReadableException.
	 *
	 * @param exception - HttpMessageNotReadableException.
	 * @return - ResponseEntity<ErrorResponse>.
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleNotReadableException(HttpMessageNotReadableException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ErrorResponse.builder().message("Request body fields has wrong format").build());
	}

	/**
	 * Method handleMissingParameter.
	 * Handling AmountSelectMethodNotFoundException.
	 *
	 * @param exception - AmountSelectMethodNotFoundException.
	 * @return - ResponseEntity<ErrorResponse>.
	 */
	@ExceptionHandler(AmountSelectMethodNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleMissingParameter(AmountSelectMethodNotFoundException exception) {
		return ResponseEntity.badRequest().body(ErrorResponse.builder().message("Wrong path parameter").build());
	}

	/**
	 * Method handleEmptyResult.
	 * Handling EmptyResultException.
	 *
	 * @param exception - EmptyResultException.
	 * @return - ResponseEntity<ErrorResponse>.
	 */
	@ExceptionHandler(EmptyResultException.class)
	public ResponseEntity<?> handleEmptyResult(EmptyResultException exception) {
		log.warn("request has no results");
		return ResponseEntity.ok().body(ErrorResponse.builder().message("No results").build());
	}


	/**
	 * Method handleArgumentTypeMismatch.
	 * Handling MethodArgumentTypeMismatchException.
	 *
	 * @param exception - MethodArgumentTypeMismatchException.
	 * @return - ResponseEntity<ErrorResponse>.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
		return ResponseEntity.ok().body(ErrorResponse.builder().message("Wrong type of path arguments").build());
	}

}
