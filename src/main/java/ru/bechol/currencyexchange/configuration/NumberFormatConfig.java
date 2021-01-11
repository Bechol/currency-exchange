package ru.bechol.currencyexchange.configuration;

import org.springframework.context.annotation.*;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Class NumberFormatConfig.
 * Bean of NumberFormat for formatting results.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Configuration
public class NumberFormatConfig {

	@Bean
	public NumberFormat numberFormat() {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat;
	}
}
