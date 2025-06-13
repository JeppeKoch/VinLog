package dat.DAOs.impl;

import dat.DTOs.ArtistDTO;
import dat.entities.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class ArtistDAO {

    private static EntityManagerFactory emf;

    public ArtistDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }



    public static ArtistDTO read(Long id){
        try (EntityManager em = emf.createEntityManager()) {
            Artist artist = em.find(Artist.class, id);
            return artist != null ? new ArtistDTO(artist) : null;
        }
    }

    public List<ArtistDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<ArtistDTO> query = em.createQuery("SELECT new dat.DTOs.ArtistDTO(r) FROM Artist r", ArtistDTO.class);
            return query.getResultList();
        }
    }

    public ArtistDTO readByName(String name){
        try (EntityManager em = emf.createEntityManager()) {
            Artist artist = em.find(Artist.class, name);
            return artist != null ? new ArtistDTO(artist) : null;
        }
    }

    public ArtistDTO create(ArtistDTO artistDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Artist artist = new Artist(artistDTO);
            if (artistDTO.getName() != null) {
                artist = em.merge(artist);
            } else {
                em.persist(artistDTO);
            }
            //em.persist(spice);
            em.getTransaction().commit();
            return new ArtistDTO(artist);
        }
    }


    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Artist artist = em.find(Artist.class, id);
            if (artist != null){
                em.remove(artist);
            }
            em.getTransaction().commit();
        }
    }

    public ArtistDTO update(Long id, ArtistDTO artistDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Artist c = em.find(Artist.class,id);
            c.setName(artistDTO.getName());

            Artist newArtist = em.merge(c);
            em.getTransaction().commit();
            return new ArtistDTO(newArtist);
        }
    }

}
