package com.edu.framework.atividadefisica.utils;

import java.util.Objects;

public class AuthenticationHelper {

    private static Integer generatedToken;

    public static Integer generate() {
        int token = 0;
        for (int i = 5; i >= 0; i--) {
            int randomNumber = getRandomNumber();
            token += (randomNumber * Math.pow(10, i));
        }
        token *= 10;
        
        while (token % 2 != 0 || token % 3 != 0 || token % 5 != 0) {
            token++;
        }

        generatedToken = token;

        return token;
    }

    public static Integer getGenerateToken() {
        return generatedToken;
    }

    public static boolean isTokenValidate() {
        if (Objects.nonNull(generatedToken)) {
            return generatedToken % 2 == 0 && generatedToken % 3 == 0 && generatedToken % 5 == 0;
        } 
        return false;
    }

    public static boolean isTokenValidate(Integer token) {
        if (Objects.nonNull(token)) {
            return token % 2 == 0 && token % 3 == 0 && token % 5 == 0;
        } 
        return false;
    }


    private static Integer getRandomNumber() {
        return (int) ((Math.random() * 9) + 1);
    }

}
