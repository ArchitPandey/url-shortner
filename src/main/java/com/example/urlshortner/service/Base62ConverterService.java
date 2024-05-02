package com.example.urlshortner.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Base62ConverterService {

    private char[] base62Symbols;

    public Base62ConverterService() {
        this.base62Symbols = new char[62];
        initBase62Symbols();
    }

    public String longToBase62(long value) {
        StringBuilder base62Sb = new StringBuilder();

        long v = value;

        while(v > 0) {
            int rem = (int)(v % 62);
            v = (v/62);
            base62Sb.append(this.base62Symbols[rem]);
        }

        return base62Sb.reverse().toString();
    }

    private void initBase62Symbols() {
        int idx = 0;

        char zero = '0';
        for(int i=0; i< 10; i++) {
            this.base62Symbols[idx++] = (char) ( (int)zero + i);
        }

        char smallA = 'a';
        for(int i=0; i<26; i++) {
            this.base62Symbols[idx++] = (char) ( (int) smallA + i );
        }

        char bigA = 'A';
        for(int i=0; i<26; i++) {
            this.base62Symbols[idx++] = (char) ( (int) bigA + i );
        }

    }

}
