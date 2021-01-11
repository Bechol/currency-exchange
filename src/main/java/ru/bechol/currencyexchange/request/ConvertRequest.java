package ru.bechol.currencyexchange.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.bechol.currencyexchange.request.validation.ValidCurrency;

import javax.validation.constraints.*;

/**
 * Class ConvertRequest.
 * Serialization request body.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConvertRequest {

	final static String REF_CURRENCY_NOT_FOUND = "reference currency not found";
	final static String TARGET_CURRENCY_NOT_FOUND = "target currency not found";
	final static String INCORRECT_AMT = "incorrect amount";
	final static String NOT_NULL_AMT = "amount is null";
	final static String INCORRECT_USER_EMAIL = "incorrect email";


	@JsonProperty(value = "email", required = true)
	@NotEmpty(message = INCORRECT_USER_EMAIL)
	@Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
			message = INCORRECT_USER_EMAIL)
	String userEmail;

	@JsonProperty(value = "reference", required = true)
	@ValidCurrency(message = REF_CURRENCY_NOT_FOUND)
	String referenceCurrency;

	@JsonProperty(value = "target", required = true)
	@ValidCurrency(message = TARGET_CURRENCY_NOT_FOUND)
	String targetCurrency;

	@NotNull(message = NOT_NULL_AMT)
	@DecimalMin(value = "0.0", inclusive = false, message = INCORRECT_AMT)
	Double amount;
}
