package com.zyct.ehome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class EhomeApplication  {

    public static void main(String[] args) {
        SpringApplication.run(EhomeApplication.class, args);
    }


}
