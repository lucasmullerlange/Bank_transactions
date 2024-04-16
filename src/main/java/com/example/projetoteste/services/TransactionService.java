package com.example.projetoteste.services;

import com.example.projetoteste.domain.transaction.Transaction;
import com.example.projetoteste.domain.transaction.TransactionDTO;
import com.example.projetoteste.domain.user.User;
import com.example.projetoteste.repositories.TransactionsRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private NotificationService notificationService;



    private TransactionsRepository repository;

    public Transaction createTransaction(TransactionDTO transactionDTO)  throws Exception {
            var payer  = this.userService.findUserById(transactionDTO.payeeId());
            var payee  = this.userService.findUserById(transactionDTO.payeeId());

            userService.validateUser(payer, transactionDTO.amount());

            boolean isAutorize = authorizeTransaction();

            if(!isAutorize ){
                throw  new Exception("transação não autorizada ");
            }


            Transaction newTransaction = new Transaction();
            newTransaction.setAmount((transactionDTO.amount()));
            newTransaction.setPayer(payer);
            newTransaction.setPayee(payee);
            newTransaction.setTransactionTime(LocalDateTime.now());

            payer.setBalance(payer.getBalance().subtract(transactionDTO.amount()));
            payee.setBalance(payee.getBalance().add(transactionDTO.amount()));

            this.repository.save(newTransaction);
            this.userService.saveUser(payer);
            this.userService.saveUser(payee);

            notificationService.sendNotification(payer,"Transação realizada com sucesso ");

            notificationService.sendNotification(payee, "Transação recebida realizada ");

            return newTransaction;
    }

    public boolean authorizeTransaction () {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);


        if (response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else return false;
    }

}
