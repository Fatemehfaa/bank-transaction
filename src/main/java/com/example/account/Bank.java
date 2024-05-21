package com.example.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bank {

    List<Account> accounts = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();

    private Account getAccount(long id){
       return accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow();

    }



    private void processTransaction(Transaction transaction) {
        Account accountFrom = getAccount(transaction.getFrom());
        Account accountTo = getAccount(transaction.getTo());


        //lock account
        synchronized (accountFrom){
            accountFrom.setBalance(accountFrom.getBalance() - transaction.getAmount());
        }
        synchronized (accountTo) {
            accountTo.setBalance(accountTo.getBalance() + transaction.getAmount());
        }

    }


    public void executeTransactions() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (Transaction transaction : transactions) {
            executor.execute(() -> processTransaction(transaction));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Account account : accounts) {
            System.out.println(account);
        }
    }




}
