package ru.bechol.currencyexchange.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class ExchangeResultResponse {

	String result;
}
