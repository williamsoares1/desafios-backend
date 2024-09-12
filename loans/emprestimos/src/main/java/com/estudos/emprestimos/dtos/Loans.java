package com.estudos.emprestimos.dtos;

import java.util.List;

import lombok.Builder;

@Builder
public record Loans(String customer, List<Loan> loans) {

}
