package ru.bechol.currencyexchange.request.validation;


import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation ValidCurrency.
 * Currency code validation.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CurrencyValidator.class)
@Documented
public @interface ValidCurrency {

	String message() default "role not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
