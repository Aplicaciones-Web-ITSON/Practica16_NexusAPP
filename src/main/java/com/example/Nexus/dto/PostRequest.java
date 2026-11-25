package com.example.Nexus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Datos que el cliente envía para crear un post.
 *
 * { "title": "Mi post", "content": "Contenido del post", "userId": 1 }
 *
 * ⚠ El userId lo decide el cliente: puede publicar a nombre de
 * cualquiera. En la versión JWT este campo se elimina.
 */
@Data
public class PostRequest {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    @NotBlank(message = "El contenido es obligatorio")
    private String content;

    @NotNull(message = "El userId es obligatorio")
    private Long userId;
}
