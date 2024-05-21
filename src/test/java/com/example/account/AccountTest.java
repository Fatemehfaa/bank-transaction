package com.example.account;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void test() {
            try {
                File file = ResourceUtils.getFile("classpath:accounts.csv");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                System.out.println(bufferedReader.lines().toList());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
