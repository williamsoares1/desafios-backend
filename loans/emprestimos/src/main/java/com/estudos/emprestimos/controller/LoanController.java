package com.estudos.emprestimos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.emprestimos.dtos.Customer;
import com.estudos.emprestimos.dtos.Loan;
import com.estudos.emprestimos.dtos.Loans;
import com.estudos.emprestimos.service.impl.ConsignmentLoanImpl;
import com.estudos.emprestimos.service.impl.GuaranteedLoanImpl;
import com.estudos.emprestimos.service.impl.PersonalLoanImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customer-loans")
public class LoanController {

    @Autowired
    private PersonalLoanImpl personalLoanImpl;

    @Autowired
    private GuaranteedLoanImpl guaranteedLoanImpl;

    @Autowired
    private ConsignmentLoanImpl consignmentLoanImpl;

    @PostMapping
    public ResponseEntity<Loans> loans(@RequestBody Customer customer) {
        List<Loan> loans = new ArrayList<>();

        Loan personal = Loan.builder().type("PERSONAL").interestRate(4).build();
        Loan guaranteed = Loan.builder().type("GUARANTEED").interestRate(3).build();
        Loan consignment = Loan.builder().type("CONSIGNMENT").interestRate(2).build();

        if(personalLoanImpl.IsElegible(customer)){
            loans.add(personal);
        }

        if(guaranteedLoanImpl.IsElegible(customer)){
            loans.add(guaranteed);
        }

        if(consignmentLoanImpl.IsElegible(customer)){
            loans.add(consignment);
        }

        return ResponseEntity.ok(Loans.builder().customer(customer.name()).loans(loans).build());
    }

}
