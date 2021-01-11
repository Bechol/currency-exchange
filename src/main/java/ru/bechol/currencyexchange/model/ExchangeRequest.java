package ru.bechol.currencyexchange.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Class ExchangeRequest.
 * Domain model for user requests.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "exchange_requests")
@NoArgsConstructor
public class ExchangeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Column(name = "user_email", nullable = false)
	String userEmail;
	@Column(name = "reference_currency", nullable = false)
	String referenceCurrency;
	@Column(name = "target_currency", nullable = false)
	String targetCurrency;
	@Column(nullable = false)
	double amount;
	@Column(nullable = false)
	double result;
	@Column(nullable = false)
	double rate;
}
