package it.epicode.gestione_viaggi.exceptions;

import lombok.Data;

@Data
public class Error {
    private String message;
    private String details;
    private String status;
}
