package ru.bechol.currencyexchange.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bechol.currencyexchange.model.ExchangeRequest;

import java.util.*;

/**
 * Interface ExchangeRequestRepository.
 * Repository layer for user requests.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {
	/**
	 * Method findAllByAmountGreaterThan.
	 * Returns list of users who requested the conversion more than the specified value in one query.
	 *
	 * @param amount specified value.
	 * @return list of users.
	 */
	Optional<List<ExchangeRequest>> findAllByAmountGreaterThan(double amount);

	/**
	 * Method findAllByAmountLessThan.
	 * Returns list of users who requested the conversion less than the specified value in one query.
	 *
	 * @param amount specified value.
	 * @return list of users.
	 */
	Optional<List<ExchangeRequest>> findAllByAmountLessThan(double amount);

	/**
	 * Method findByTotalAmountGreaterThen.
	 * Returns the list of users whose total requested volume exceeds the specified value.
	 *
	 * @param amount specified value.
	 * @return list of users.
	 */
	@Query("select er.userEmail from ExchangeRequest er group by er.userEmail having sum(er.amount) > :amount")
	Optional<List<String>> findByTotalAmountGreaterThen(@Param("amount") double amount);

}
