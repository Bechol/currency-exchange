package ru.bechol.currencyexchange.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bechol.currencyexchange.request.ConvertRequest;
import ru.bechol.currencyexchange.service.ExchangeService;

import javax.validation.Valid;

import static ru.bechol.currencyexchange.response.ErrorMapHelper.createBindingErrorResponse;

/**
 * Class CurrencyController.
 * REST controller. Currency exchange operations.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Tag(name = "/exchange", description = "Currency exchange")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RestController
@RequestMapping("/exchange")
public class CurrencyController {

	ExchangeService exchangeService;

	@Autowired
	public CurrencyController(@Qualifier("exchangeServiceImpl") ExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}

	/**
	 * Method change.
	 * Currency exchange.
	 *
	 * @param convertRequest serialized request body.
	 * @param bindingResult  validation results.
	 * @return response with currency exchange result.
	 */
	@Operation(summary = "Currency exchange request", description = "Send request to openexchangerates.org for to get " +
			"currency exchange rate, then calculate and save exchange operation to database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok",
					content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
							@ExampleObject(value = "{\n\t\"result\": \"1500 $\"\n}")})
					}),
			@ApiResponse(responseCode = "400", description = "Validation errors",
					content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
							@ExampleObject(value = "{\n\t\"result\": false,\n\t\"errors\": {\n" +
									"\t\t\"email\": [\n\"incorrect email \"\n]," +
									"\t\t\"reference\": [\n\"reference currency not found\"\n]," +
									"\t\t\"target\": [\n\"target currency not found]\"\n]," +
									"\t\t\"amount\": [\n\"amount is null\"\n]\n}\n}")})
					}),
			@ApiResponse(responseCode = "404", description = "HttpMessageNotReadableException",
					content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
							@ExampleObject(value = "{\n\t\"result\": false,\n\t\"message\": " +
									"\"Request body fields has wrong format\"\n}")})
					})
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> change(@Valid @RequestBody ConvertRequest convertRequest,
									BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return createBindingErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		return exchangeService.convert(convertRequest);
	}
}
