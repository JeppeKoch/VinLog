package dat.DAOs.impl;

import dat.DTOs.UserDTO;
import dat.entities.User;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class UserDAO {

    private static EntityManagerFactory emf;

    public UserDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }



    public static UserDTO read(Long id){
        try (EntityManager em = emf.createEntityManager()) {
            User user = em.find(User.class, id);
            return user != null ? new UserDTO(user) : null;
        }
    }

    public List<UserDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<UserDTO> query = em.createQuery("SELECT new dat.DTOs.UserDTO(r) FROM User r", UserDTO.class);
            return query.getResultList();
        }
    }

    public UserDTO readByName(String name){
        try (EntityManager em = emf.createEntityManager()) {
            User user = em.find(User.class, name);
            return user != null ? new UserDTO(user) : null;
        }
    }

    public UserDTO create(UserDTO userDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = new User(userDTO);
            if (userDTO.getUsername() != null) {
                user = em.merge(user);
            } else {
                em.persist(user);
            }
            //em.persist(spice);
            em.getTransaction().commit();
            return new UserDTO(user);
        }
    }


    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null){
                em.remove(user);
            }
            em.getTransaction().commit();
        }
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            User c = em.find(User.class,id);
            c.setUsername(userDTO.getUsername());
            c.setEmail(userDTO.getEmail());

            User newUser = em.merge(c);
            em.getTransaction().commit();
            return new UserDTO(newUser);
        }
    }

}
