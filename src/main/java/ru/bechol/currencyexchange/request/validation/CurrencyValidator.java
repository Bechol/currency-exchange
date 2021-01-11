package ru.bechol.currencyexchange.request.validation;

import org.apache.logging.log4j.util.Strings;

import javax.validation.*;
import java.util.Currency;

/**
 * Class CurrencyValidator.
 * Validation of user input for currency code;
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

	@Override
	public void initialize(ValidCurrency constraintAnnotation) {

	}

	@Override
	public boolean isValid(String currencyCode, ConstraintValidatorContext constraintValidatorContext) {
		return Strings.isNotEmpty(currencyCode) && isCurrencyCodeExist(currencyCode);
	}

	private boolean isCurrencyCodeExist(String currencyCode) {
		return Currency.getAvailableCurrencies().stream()
				.anyMatch(currency -> currency.getCurrencyCode().equals(currencyCode));
	}
}
