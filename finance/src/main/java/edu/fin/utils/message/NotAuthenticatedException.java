package edu.fin.utils.message;

public class NotAuthenticatedException extends RuntimeException {
    public NotAuthenticatedException() {
        super("User not authenticated");
    }
}
