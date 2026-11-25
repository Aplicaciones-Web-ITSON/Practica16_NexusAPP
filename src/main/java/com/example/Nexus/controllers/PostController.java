package com.example.Nexus.controllers;

import com.example.Nexus.dto.PostRequest;
import com.example.Nexus.models.Post;
import com.example.Nexus.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ============================================================
 *  PostController — Publicaciones (versión SIN seguridad)
 * ============================================================
 *
 *   GET  /api/posts                → todos los posts (el feed)
 *   GET  /api/posts/user/{userId}  → posts de un usuario
 *   POST /api/posts                → crear un post
 *
 * ⚠ VULNERABILIDAD INTENCIONAL (para comparar con la versión JWT):
 * El servidor confía en el userId que manda el cliente.
 * Cualquiera puede publicar a nombre de otro o ver los posts de
 * otro con solo cambiar el número. En la versión JWT el usuario
 * se obtiene del token y estas rutas desaparecen.
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /** GET /api/posts → todos los posts, del más reciente al más antiguo. */
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    /** GET /api/posts/user/{userId} → posts del usuario que diga la URL (¡cualquiera!). */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUser(userId));
    }

    /**
     * POST /api/posts → crea un post.
     * Body: { "title": "...", "content": "...", "userId": 1 }
     * El autor es el userId que mande el cliente (inseguro a propósito).
     */
    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest request) {
        Post post = postService.createPost(request);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
