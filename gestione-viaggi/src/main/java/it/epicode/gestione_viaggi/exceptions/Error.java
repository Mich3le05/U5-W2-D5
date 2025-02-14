package it.epicode.gestione_viaggi.exceptions;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private String message;
    private String details;
    private String status;
}