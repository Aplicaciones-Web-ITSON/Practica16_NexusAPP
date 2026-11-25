# 🚀 NexusAPI (sin JWT)

API REST de una red social básica (usuarios, posts y comentarios) hecha con **Spring Boot 4 + MySQL**.

> ⚠️ **Versión vulnerable a propósito.** El servidor confía en el `userId` que manda el cliente, así que cualquiera puede publicar o comentar a nombre de otro. Se usa para compararla con [NexusAPI con JWT](https://github.com/Aplicaciones-Web-ITSON/Practica16_NexusAPP_JWT).

---

## ▶️ Arranque rápido

1. Tener MySQL corriendo. La base `nexus` se crea sola.
2. Poner tus credenciales en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=tu_password
   ```
3. Ejecutar `NexusApplication.java` desde IntelliJ o en terminal:
   ```bash
   .\mvnw.cmd spring-boot:run     # Windows
   ./mvnw spring-boot:run         # macOS / Linux
   ```
4. La API queda en **`http://localhost:8080/api`**.
5. (Opcional) Cargar los [datos de prueba](#-datos-de-prueba).

---

## 🔗 Endpoints

Todos son públicos (no hay login).

| Método | Ruta | Body | Qué hace |
|---|---|---|---|
| POST | `/api/users` | `{ "username", "email", "password" }` | Crea una cuenta |
| GET | `/api/posts` | — | Todos los posts (feed) |
| GET | `/api/posts/user/{userId}` | — | Posts de **cualquier** usuario |
| POST | `/api/posts` | `{ "title", "content", "userId" }` | Publica a nombre del `userId` que mandes |
| GET | `/api/comments/post/{postId}` | — | Comentarios de un post |
| POST | `/api/comments` | `{ "postId", "text", "userId" }` | Comenta a nombre del `userId` que mandes |

### Errores

| Código | Cuándo |
|---|---|
| 400 | Faltan campos, JSON mal formado o el username ya existe |
| 404 | El usuario, el post o la ruta no existen |

---

## 🕳️ ¿Dónde está el problema?

```json
POST /api/posts
{ "title": "Hackeado", "content": "No lo escribí yo", "userId": 1 }
```

El servidor no tiene forma de saber **quién** hace la petición: usa el `userId` del body tal cual. La [versión con JWT](https://github.com/Aplicaciones-Web-ITSON/Practica16_NexusAPP_JWT) elimina ese campo y obtiene al usuario del token.

---

## 🌱 Datos de prueba

Ejecútalos en MySQL **después** del primer arranque, cuando Hibernate ya creó las tablas.

```sql
INSERT INTO users (username, email, password) VALUES
('carlos_dev',   'carlos@nexus.com', '1234'),
('maria_ux',     'maria@nexus.com',  '1234'),
('juan_backend', 'juan@nexus.com',   '1234'),
('laura_design', 'laura@nexus.com',  '1234'),
('pedro_qa',     'pedro@nexus.com',  '1234');

INSERT INTO posts (title, content, created_at, user_id) VALUES
('Bienvenidos a Nexus', 'Esta es la primera publicación de la plataforma. ¡Esperamos que disfruten la experiencia!', NOW(), 1),
('Tips de UI/UX para principiantes', 'Hoy les comparto 5 tips esenciales para mejorar la experiencia de usuario en sus aplicaciones...', NOW(), 2),
('Spring Boot con MySQL paso a paso', 'En este post explico cómo configurar Spring Boot con una base de datos MySQL correctamente.', NOW(), 3),
('¿Qué es el diseño minimalista?', 'El diseño minimalista se basa en la simplicidad, eliminando lo innecesario para destacar lo esencial.', NOW(), 4),
('Cómo hacer pruebas unitarias en Java', 'Las pruebas unitarias son fundamentales para garantizar la calidad del software. Aquí un ejemplo con JUnit...', NOW(), 5),
('React vs Angular en 2025', 'Comparativa detallada de dos de los frameworks más populares del frontend actual.', NOW(), 1),
('Bases de datos NoSQL: ¿cuándo usarlas?', 'MongoDB, Redis, Cassandra... ¿cuál elegir y cuándo? Guía práctica para developers.', NOW(), 3);

INSERT INTO comments (text, created_at, post_id, user_id) VALUES
('¡Excelente iniciativa! Esperando más contenido.', NOW(), 1, 2),
('Muy buena bienvenida, el equipo se ve muy profesional.', NOW(), 1, 3),
('Justo lo que necesitaba, gracias por los tips!', NOW(), 2, 1),
('Muy claro el tutorial, me ayudó a resolver mi error de conexión.', NOW(), 3, 2),
('Me encanta el enfoque minimalista, menos es más!', NOW(), 4, 1),
('JUnit es genial, ¿conoces Mockito también?', NOW(), 5, 1),
('Yo sigo prefiriendo React por su ecosistema.', NOW(), 6, 4),
('MongoDB para datos no estructurados es una maravilla.', NOW(), 7, 2);
```
