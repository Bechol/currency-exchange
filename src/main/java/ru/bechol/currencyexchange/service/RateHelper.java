package ru.bechol.currencyexchange.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.bechol.currencyexchange.configuration.OpenExchangeRatesConfig;
import ru.bechol.currencyexchange.response.RateResponse;

import java.net.URI;
import java.util.Objects;

/**
 * Class RateHelper.
 * Get currency exchange rate.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RateHelper {

	OpenExchangeRatesConfig openExchangeRatesConfig;
	RestTemplate restTemplate;

	@Autowired
	public RateHelper(OpenExchangeRatesConfig openExchangeRatesConfig, RestTemplate restTemplate) {
		this.openExchangeRatesConfig = openExchangeRatesConfig;
		this.restTemplate = restTemplate;
	}

	/**
	 * Method getCurrencyExchangeRate.
	 * Get currency exchange rate.
	 *
	 * @param referenceCurrencyCode reference currency code.
	 * @param targetCurrencyCode    target currency code.
	 * @return double result.
	 */
	public double getCurrencyExchangeRate(String referenceCurrencyCode, String targetCurrencyCode) {
		RateResponse rateResponse = restTemplate.getForObject(
				createRateRequestUri(referenceCurrencyCode, targetCurrencyCode), RateResponse.class
		);
		return Objects.nonNull(rateResponse) ? rateResponse.getRates().get(targetCurrencyCode) : 0D;
	}

	/**
	 * Method createRateRequestUri.
	 * Creating uri for request.
	 *
	 * @param referenceCurrencyCode reference currency code.
	 * @param targetCurrencyCode    target currency code.
	 * @return request URI.
	 */
	private URI createRateRequestUri(String referenceCurrencyCode, String targetCurrencyCode) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openExchangeRatesConfig.getBaseUrl())
				.queryParam("app_id", openExchangeRatesConfig.getAppId())
				.queryParam("base", referenceCurrencyCode)
				.queryParam("symbols", targetCurrencyCode)
				.queryParam("prettyprint", true);
		log.info("Request for get change rate from {} to {}: [{}]",
				referenceCurrencyCode, targetCurrencyCode, builder.toUriString());
		return URI.create(builder.toUriString());
	}
}
