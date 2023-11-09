package com.vivomoney.dtos;

import java.math.BigDecimal;

public record OfferDTO(BigDecimal taxaMensal, Long valor, Long numeroParcelas) {
}
