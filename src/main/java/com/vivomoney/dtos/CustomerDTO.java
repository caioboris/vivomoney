package com.vivomoney.dtos;

import java.time.LocalDate;

public record CustomerDTO(String cpf, String nome, LocalDate data_de_nascimento) {
}
