package com.example.projetoteste.controller;


import com.example.projetoteste.domain.transaction.TransactionDTO;
import com.example.projetoteste.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {



    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public HttpStatus createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
            var newTransaction = this.transactionService.createTransaction(transactionDTO);
            return HttpStatus.CREATED;
    }

}
