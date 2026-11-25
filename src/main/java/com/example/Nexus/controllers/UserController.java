package com.example.Nexus.controllers;

import com.example.Nexus.dto.UserRequest;
import com.example.Nexus.models.User;
import com.example.Nexus.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ============================================================
 *  UserController — Registro de usuarios
 * ============================================================
 *
 *   POST /api/users → crear cuenta
 *
 * En esta versión no hay login: no existe forma de saber
 * quién hace cada petición. En la versión JWT el registro
 * pasa a /api/auth/register y se agrega /api/auth/login.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registra un nuevo usuario.
     * Body: { "username": "ana", "email": "ana@nexus.com", "password": "1234" }
     *
     * @return 201 Created con el usuario creado (sin password)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }
}
