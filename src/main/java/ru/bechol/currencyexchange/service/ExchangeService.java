package ru.bechol.currencyexchange.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.bechol.currencyexchange.request.ConvertRequest;

/**
 * Interface ExchangeService.
 * Service layer.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Service
public interface ExchangeService {

	/**
	 * Method convert.
	 * Currency exchange.
	 *
	 * @param convertRequest serialized and validated request body.
	 * @return ResponseEntity.
	 */
	ResponseEntity<?> convert(ConvertRequest convertRequest);
}
