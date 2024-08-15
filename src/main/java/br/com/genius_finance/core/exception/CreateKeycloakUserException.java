package br.com.genius_finance.core.exception;

public class CreateKeycloakUserException extends RuntimeException {

    public CreateKeycloakUserException() {
        super("error while creating keycloak user");
    }
    
}
