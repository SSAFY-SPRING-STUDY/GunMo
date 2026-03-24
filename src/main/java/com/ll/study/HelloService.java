package com.ll.study;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hi() {
        return "Hello, World!";
    }
}
