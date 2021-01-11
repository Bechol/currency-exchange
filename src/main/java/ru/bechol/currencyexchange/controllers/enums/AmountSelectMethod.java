package ru.bechol.currencyexchange.controllers.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.bechol.currencyexchange.service.exception.AmountSelectMethodNotFoundException;

import java.util.stream.Stream;

/**
 * Enum AmountSelectMethod.
 * Available types of selection.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum AmountSelectMethod {

	MORE_THAN("more"), LESS_THAN("less");

	String value;

	/**
	 * Method fromValue.
	 * Get enum by string value.
	 *
	 * @param value value to search.
	 * @return AmountSelectMethod enum value.
	 * @throws AmountSelectMethodNotFoundException if enum value not found.
	 */
	public static AmountSelectMethod fromValue(String value) throws AmountSelectMethodNotFoundException {
		return Stream.of(values())
				.filter(selectMethod -> selectMethod.value.equals(value)).findAny()
				.orElseThrow(AmountSelectMethodNotFoundException::new);
	}
}
