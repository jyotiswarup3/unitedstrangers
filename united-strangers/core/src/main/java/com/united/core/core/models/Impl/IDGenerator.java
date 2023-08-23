package com.united.core.core.models.Impl;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class IDGenerator {
    public static String generateUniqueID(int n) {
        // Length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        // Create a StringBuffer to store the result
        String randomString = new String(array, StandardCharsets.UTF_8);
        StringBuilder r = new StringBuilder();

        // Append numeric characters
        // from the generated random String into the result

        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if ((ch >= '0' && ch <= '9') && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        return r.toString();
    }
}