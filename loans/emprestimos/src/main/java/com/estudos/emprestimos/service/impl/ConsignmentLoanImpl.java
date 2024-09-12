package com.estudos.emprestimos.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.estudos.emprestimos.dtos.Customer;
import com.estudos.emprestimos.service.LoanService;

@Service
public class ConsignmentLoanImpl implements LoanService {

    @Override
    public boolean IsElegible(Customer customer) {
        return customer.income().compareTo(new BigDecimal(5000.00)) != -1;
    }

}
