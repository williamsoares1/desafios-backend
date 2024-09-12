package com.estudos.emprestimos.dtos;

import lombok.Builder;

@Builder
public record Loan(String type, Integer interestRate) {

}
