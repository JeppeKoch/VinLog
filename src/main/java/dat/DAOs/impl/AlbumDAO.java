package dat.DAOs.impl;

import dat.DTOs.AlbumDTO;
import dat.entities.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class AlbumDAO {

    private static EntityManagerFactory emf;

    public AlbumDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }



    public static AlbumDTO read(Long id){
        try (EntityManager em = emf.createEntityManager()) {
            Album album = em.find(Album.class, id);
            return album != null ? new AlbumDTO(album) : null;
        }
    }

    public List<AlbumDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<AlbumDTO> query = em.createQuery("SELECT new dat.DTOs.AlbumDTO(r) FROM Album r", AlbumDTO.class);
            return query.getResultList();
        }
    }

    public AlbumDTO readByName(String name){
        try (EntityManager em = emf.createEntityManager()) {
            Album album = em.find(Album.class, name);
            return album != null ? new AlbumDTO(album) : null;
        }
    }

    public AlbumDTO create(AlbumDTO albumDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Album album = new Album(albumDTO);
            if (albumDTO.getTitle() != null) {
                album = em.merge(album);
            } else {
                em.persist(album);
            }
            //em.persist(spice);
            em.getTransaction().commit();
            return new AlbumDTO(album);
        }
    }


    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Album album = em.find(Album.class, id);
            if (album != null){
                em.remove(album);
            }
            em.getTransaction().commit();
        }
    }

    public AlbumDTO update(Long id, AlbumDTO albumDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Album c = em.find(Album.class,id);
            c.setTitle(albumDTO.getTitle());

            Album newAlbum = em.merge(c);
            em.getTransaction().commit();
            return new AlbumDTO(newAlbum);
        }
    }
}
