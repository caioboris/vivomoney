package com.vivomoney.dtos;

import java.math.BigDecimal;

public record OfferDTO(BigDecimal taxa_mensal, Long valor, Long numero_parcelas) {
}
