package com.example.Nexus.services;

import com.example.Nexus.dto.PostRequest;
import com.example.Nexus.exceptions.ResourceNotFoundException;
import com.example.Nexus.models.Post;
import com.example.Nexus.models.User;
import com.example.Nexus.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Lógica de negocio de posts.
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    /** Crea un post a nombre del userId que mandó el cliente. */
    public Post createPost(PostRequest request) {
        User user = userService.getUserById(request.getUserId());

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(user);
        return postRepository.save(post);
    }

    /** Todos los posts, del más reciente al más antiguo. */
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    /** Posts de un usuario. 404 si el usuario no existe. */
    public List<Post> getPostsByUser(Long userId) {
        userService.getUserById(userId);
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    /** Busca un post por id (se usa al comentar). 404 si no existe. */
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post no encontrado con ID: " + id));
    }
}
