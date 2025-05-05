/*
 * NotAuthenticatedException.java
 * 
 * This class represents an exception that is thrown when a user is not authenticated.
 */
package edu.fin.utils.message;

public class NotAuthenticatedException extends RuntimeException {
    public NotAuthenticatedException() {
        super("User not authenticated");
    }
}
