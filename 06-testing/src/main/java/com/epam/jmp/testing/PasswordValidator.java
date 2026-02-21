package com.epam.jmp.testing;



public class PasswordValidator {

    public static boolean validate(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit;
    }

}
