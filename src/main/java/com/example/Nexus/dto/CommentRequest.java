package com.example.Nexus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Datos que el cliente envía para comentar un post.
 *
 * { "postId": 1, "text": "Buen post", "userId": 2 }
 *
 * ⚠ El userId lo decide el cliente: puede comentar a nombre de
 * cualquiera. En la versión JWT este campo se elimina.
 */
@Data
public class CommentRequest {

    @NotBlank(message = "El texto es obligatorio")
    private String text;

    @NotNull(message = "El postId es obligatorio")
    private Long postId;

    @NotNull(message = "El userId es obligatorio")
    private Long userId;
}
