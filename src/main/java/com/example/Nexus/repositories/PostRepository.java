package com.example.Nexus.repositories;

import com.example.Nexus.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Acceso a la tabla "posts".
 * Spring Data genera el SQL a partir del nombre del método.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /** SELECT * FROM posts ORDER BY created_at DESC */
    List<Post> findAllByOrderByCreatedAtDesc();

    /** SELECT * FROM posts WHERE user_id = ? ORDER BY created_at DESC */
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);
}
