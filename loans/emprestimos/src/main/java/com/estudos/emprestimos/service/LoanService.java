package com.estudos.emprestimos.service;

import org.springframework.stereotype.Service;

import com.estudos.emprestimos.dtos.Customer;

@Service
public interface LoanService {
    boolean IsElegible(Customer customer);
}
