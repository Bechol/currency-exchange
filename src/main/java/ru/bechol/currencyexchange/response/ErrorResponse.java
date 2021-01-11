package ru.bechol.currencyexchange.response;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

/**
 * Class ErrorResponse.
 * Validation error response deserialization.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorResponse {

	boolean result;
	ValidationErrors errors;
	String message;

	@Getter
	@Builder
	public static class ValidationErrors {

		Map<String, List<String>> errorMap;

		@JsonAnyGetter
		public Map<String, List<String>> getErrorMap() {
			return errorMap;
		}
	}
}
