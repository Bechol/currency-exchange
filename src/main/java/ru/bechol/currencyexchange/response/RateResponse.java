package ru.bechol.currencyexchange.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

/**
 * Class RateResponse.
 * Deserialization of currency rate request.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateResponse {

	String base;
	Map<String, Double> rates;
}
