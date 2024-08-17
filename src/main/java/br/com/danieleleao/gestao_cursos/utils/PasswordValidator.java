package br.com.danieleleao.gestao_cursos.utils;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 15;
    private static final Pattern UPPER_CASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");

    public static boolean isPasswordSecure(String password) {

        return password.length() >= MIN_LENGTH
                && password.length() <= MAX_LENGTH
                && UPPER_CASE_PATTERN.matcher(password).find()
                && LOWER_CASE_PATTERN.matcher(password).find()
                && DIGIT_PATTERN.matcher(password).find();
    }

}
