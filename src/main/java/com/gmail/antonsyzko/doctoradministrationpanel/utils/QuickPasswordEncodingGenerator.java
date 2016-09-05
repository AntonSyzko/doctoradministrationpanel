package com.gmail.antonsyzko.doctoradministrationpanel.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class QuickPasswordEncodingGenerator {

    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        String password = "test";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode(password));

    }
}

