package ru.bechol.currencyexchange.configuration;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Class OpenExchangeRatesConfig.
 * Configuration of openexchangerates client.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see openexchangerates.org
 */
@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpenExchangeRatesConfig {

	String appId;
	String baseUrl;

	@Autowired
	public OpenExchangeRatesConfig(@Value("${openexchangerates.appId}") String appId,
								   @Value("${openexchangerates.baseUrl}") String baseUrl) {
		this.appId = appId;
		this.baseUrl = baseUrl;
	}
}
