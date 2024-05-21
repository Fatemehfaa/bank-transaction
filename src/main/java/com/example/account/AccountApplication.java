package com.example.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
        Bank bank = new Bank();

        List<String> strings = Utils.readFileLines("accounts.csv");
        bank.setAccounts(strings.stream()
                .map(string -> string.split(",", 3))
                .map(acc -> Account.builder()
                        .id(Long.parseLong(acc[0]))
                        .name(acc[1])
                        .balance(Long.parseLong(acc[2]))
                        .build()
                ).toList()
        );

        List<String> strings1 = Utils.readFileLines("transactions.csv");
        bank.setTransactions(
                strings1.stream()
                        .map(string -> string.split(",", 3))
                        .map(trans -> Transaction.builder()
                                .from(Long.parseLong(trans[0]))
                                .to(Long.parseLong(trans[1]))
                                .amount(
                                        Long.parseLong(
                                                trans[2]
                                                        .replaceAll("\"", "")
                                                        .replace(",", "")
                                        )
                                )
                                .build()
                        )
                        .toList()
        );

        bank.executeTransactions();
    }






}
