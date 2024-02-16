package com.example.apiturismo.service;

import org.springframework.stereotype.Service;

/**
 * Servicio de seguridad que proporciona funciones relacionadas con la validación de tokens.
 */
@Service
public class SecurityService {
    /**
     * Método que valida un token comparándolo con un valor predeterminado.
     *
     * @param token El token a validar.
     * @return {@code true} si el token es igual al valor predeterminado, {@code false} de lo contrario.
     */
    public Boolean tokenDeValidacion(String token) {
        return (token.equals("t0k3n"));
    }
}

