package com.estudos.emprestimos.dtos;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record Customer(Integer age, String cpf, String name, BigDecimal income, String location) {

}
