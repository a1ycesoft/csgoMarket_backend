package com.cs.csgo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Csgo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Csgo2Application.class, args);
    }

}
