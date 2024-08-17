package br.com.danieleleao.gestao_cursos.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
