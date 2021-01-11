package ru.bechol.currencyexchange.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.bechol.currencyexchange.model.ExchangeRequest;
import ru.bechol.currencyexchange.repositories.ExchangeRequestRepository;
import ru.bechol.currencyexchange.request.ConvertRequest;
import ru.bechol.currencyexchange.response.ExchangeResultResponse;
import ru.bechol.currencyexchange.service.*;

import java.text.NumberFormat;
import java.util.Currency;

/**
 * Class ExchangeServiceImpl.
 * Implementation of ExchangeService.
 *
 * @author OLeg Bech
 * @email oleg071984@gmail.com.
 * @see ExchangeService
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class ExchangeServiceImpl implements ExchangeService {

	RateHelper rateHelper;
	NumberFormat numberFormat;
	ExchangeRequestRepository exchangeRequestRepository;

	@Autowired
	public ExchangeServiceImpl(RateHelper rateHelper, NumberFormat numberFormat,
							   ExchangeRequestRepository exchangeRequestRepository) {
		this.rateHelper = rateHelper;
		this.numberFormat = numberFormat;
		this.exchangeRequestRepository = exchangeRequestRepository;
	}

	@Override
	public ResponseEntity<?> convert(ConvertRequest convertRequest) {
		double exchangeRate = rateHelper.getCurrencyExchangeRate(convertRequest.getReferenceCurrency(),
				convertRequest.getTargetCurrency());
		double result = convertRequest.getAmount() * exchangeRate;
		if (result > 0) {
			saveRequest(convertRequest, exchangeRate, result);
		}
		return ResponseEntity.ok(buildResultString(result, convertRequest.getTargetCurrency()));
	}

	/**
	 * Method buildResultString.
	 *
	 * @param result             result of currency exchange.
	 * @param targetCurrencyCode target currency code.
	 * @return response string.
	 */
	private ExchangeResultResponse buildResultString(double result, String targetCurrencyCode) {
		numberFormat.setCurrency(Currency.getInstance(targetCurrencyCode));
		return ExchangeResultResponse.builder().result(numberFormat.format(result)).build();
	}

	/**
	 * Method saveRequest.
	 * Save results of currency exchange to db.
	 *
	 * @param convertRequest serialized request body.
	 * @param exchangeRate   currency exchange rate.
	 * @param result         result of currency exchange.
	 */
	@Async
	private void saveRequest(ConvertRequest convertRequest, double exchangeRate, double result) {
		ExchangeRequest exchangeRequest = new ExchangeRequest();
		exchangeRequest.setUserEmail(convertRequest.getUserEmail());
		exchangeRequest.setReferenceCurrency(convertRequest.getReferenceCurrency());
		exchangeRequest.setTargetCurrency(convertRequest.getTargetCurrency());
		exchangeRequest.setAmount(convertRequest.getAmount());
		exchangeRequest.setRate(exchangeRate);
		exchangeRequest.setResult(result);
		exchangeRequestRepository.save(exchangeRequest);
	}

}
