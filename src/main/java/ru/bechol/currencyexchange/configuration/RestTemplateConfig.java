package ru.bechol.currencyexchange.configuration;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Class RestTemplateConfig.
 * Create bean of RestTemplate.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
