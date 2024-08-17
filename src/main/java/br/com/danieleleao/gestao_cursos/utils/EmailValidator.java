package br.com.danieleleao.gestao_cursos.utils;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean isValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

}
