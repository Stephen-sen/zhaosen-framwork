//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhaosen.util;

import java.security.SecureRandom;

public class Util {
    public static final int RANDOM_RULE_NUMBER = 1;
    public static final int RANDOM_RULE_NUMBER_WITH_LOW_CASE = 2;
    public static final int RANDOM_RULE_NUMBER_WITH_CAPITAL = 3;
    public static final int RANDOM_RULE_NUMBER_WITH_LETTER = 4;

    public Util() {
    }

    public static synchronized String getRandomString(int length) {
        byte[] b = new byte[length];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(b);
        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        char[] out = new char[b.length];

        for(int i = 0; i < b.length; ++i) {
            int index = b[i] % alphabet.length;
            if(index < 0) {
                index += alphabet.length;
            }

            out[i] = alphabet[index];
        }

        return new String(out);
    }

    public static synchronized String getRandomString(int length, int rule) {
        byte[] b = new byte[length];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(b);
        char[] alphabet;
        if(rule == 1) {
            alphabet = "1234567890".toCharArray();
        } else if(rule == 2) {
            alphabet = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        } else if(rule == 3) {
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        } else {
            alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        }

        char[] out = new char[b.length];

        for(int i = 0; i < b.length; ++i) {
            int index = b[i] % alphabet.length;
            if(index < 0) {
                index += alphabet.length;
            }

            out[i] = alphabet[index];
        }

        return new String(out);
    }
}
