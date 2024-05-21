package com.example.account;

import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class Utils {
    public List<String> readFileLines(String fileName){
        try {
            File file = ResourceUtils.getFile("classpath:" + fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.lines().toList();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
