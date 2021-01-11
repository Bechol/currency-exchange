package ru.bechol.currencyexchange.response;

import org.springframework.http.*;
import org.springframework.validation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class ErrorMapHelper.
 * Analyzes the result of validation and forms a response to the request.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
public class ErrorMapHelper {

	/**
	 * Method createBindingErrorResponse.
	 * Analyzes the result of validation and forms a response to the request.
	 *
	 * @param bindingResult query data validation result.
	 * @param httpStatus    response status.
	 * @return response to request.
	 */
	public static ResponseEntity<?> createBindingErrorResponse(BindingResult bindingResult, HttpStatus httpStatus) {
		Map<String, List<String>> validationErrors = bindingResult.getFieldErrors().stream()
				.collect(Collectors.groupingBy(FieldError::getField,
						Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
		return ResponseEntity.status(httpStatus).body(ErrorResponse.builder()
				.result(false)
				.message("validation failed")
				.errors(ErrorResponse.ValidationErrors.builder().errorMap(validationErrors).build())
				.build()
		);
	}


}
