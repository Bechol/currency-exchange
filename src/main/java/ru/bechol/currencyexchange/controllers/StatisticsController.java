package ru.bechol.currencyexchange.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.bechol.currencyexchange.service.StatisticsService;
import ru.bechol.currencyexchange.service.exception.EmptyResultException;

import static ru.bechol.currencyexchange.controllers.enums.AmountSelectMethod.fromValue;

/**
 * Class StatisticsController.
 * REST controller. Statistics operations.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Tag(name = "/stats", description = "Exchange operation statistics")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/stats")
public class StatisticsController {

	StatisticsService statisticsService;

	@Autowired
	public StatisticsController(@Qualifier("statisticsServiceImpl") StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	/**
	 * Method getUsersByAmount.
	 * GET request /api/v1/amount/{method}/than/{amount}.
	 *
	 * @param method selection method for amount.
	 * @param amount selection amount.
	 * @return list of users.
	 * @throws Exception if request fails.
	 */
	@Operation(summary = "Users by specified conversion value", description = "Returns list of users who requested " +
			"the conversion more/less than the specified value in one query.")
	@GetMapping(value = "/amount/{method}/than/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsersByAmount(@PathVariable String method, @PathVariable double amount)
			throws Exception {
		if (amount <= 0) {
			log.warn("requested amount is negative or zero, throwing EmptyResultException");
			throw new EmptyResultException();
		}
		return statisticsService.getUsersInfoByAmount(fromValue(method), amount);
	}

	/**
	 * Method getUsersByTotalAmount.
	 * GET request /api/v1/total/{amount}.
	 *
	 * @param amount selection amount.
	 * @return list of users.
	 * @throws Exception if request fails.
	 */
	@Operation(summary = "Users by total requested volume", description = "Returns the list of users whose " +
			"total requested volume exceeds the specified value.")
	@GetMapping(value = "/total/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsersByTotalAmount(@PathVariable double amount) throws Exception {
		return statisticsService.getUsersByTotalAmount(amount);
	}

	/**
	 * Method getRating.
	 * GET request /api/v1/rating.
	 *
	 * @param amount selection amount.
	 * @return list of users.
	 */
	@Operation(summary = "Get exchange rating")
	@GetMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRating() {
		return statisticsService.getExchangeRating();
	}
}
