package br.com.marceloazvedo.exception;

public class MapperException extends Exception {

    private static final String ERROR_PREFIX_MESSAGE = "An error has occurred when try to mapper target class %s, check class field names and types are correct.\n%s";

    public MapperException(String message, Class<?> clazz) {
        super(String.format(ERROR_PREFIX_MESSAGE, clazz.getName(), message));
    }
}
