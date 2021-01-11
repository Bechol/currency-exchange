package ru.bechol.currencyexchange.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.bechol.currencyexchange.controllers.enums.AmountSelectMethod;
import ru.bechol.currencyexchange.service.exception.EmptyResultException;

/**
 * Interface StatisticsService.
 * Service layer.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Service
public interface StatisticsService {
	/**
	 * Method getUsersInfoByAmount.
	 * Returns list of users who requested the conversion more/less than the specified value in one query.
	 *
	 * @param amountSelectMethod selection method for sql query.
	 * @param amount             specified value.
	 * @return ResponseEntity.
	 * @throws EmptyResultException if request returns zero result.
	 */
	ResponseEntity<?> getUsersInfoByAmount(AmountSelectMethod amountSelectMethod, double amount)
			throws EmptyResultException;

	/**
	 * Method getUsersByTotalAmount.
	 * Returns the list of users whose total requested volume exceeds the specified value.
	 *
	 * @param value specified value.
	 * @return ResponseEntity.
	 * @throws EmptyResultException if request returns zero result.
	 */
	ResponseEntity<?> getUsersByTotalAmount(double value) throws EmptyResultException;

	/**
	 * Method getExchangeRating.
	 * Get exchange rating.
	 *
	 * @return ResponseEntity.
	 */
	ResponseEntity<?> getExchangeRating();


}
