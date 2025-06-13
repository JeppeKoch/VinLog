package dat.DAOs.impl;

import dat.DTOs.TrackDTO;
import dat.entities.Track;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class TrackDAO {

    private static EntityManagerFactory emf;

    public TrackDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }



    public static TrackDTO read(Long id){
        try (EntityManager em = emf.createEntityManager()) {
            Track track = em.find(Track.class, id);
            return track != null ? new TrackDTO(track) : null;
        }
    }

    public List<TrackDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<TrackDTO> query = em.createQuery("SELECT new dat.DTOs.TrackDTO(r) FROM Track r", TrackDTO.class);
            return query.getResultList();
        }
    }

    public TrackDTO readByName(String name){
        try (EntityManager em = emf.createEntityManager()) {
            Track track = em.find(Track.class, name);
            return track != null ? new TrackDTO(track) : null;
        }
    }

    public TrackDTO create(TrackDTO trackDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Track track = new Track(trackDTO);
            if (trackDTO.getTitle() != null) {
                track = em.merge(track);
            } else {
                em.persist(track);
            }
            //em.persist(spice);
            em.getTransaction().commit();
            return new TrackDTO(track);
        }
    }


    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Track track = em.find(Track.class, id);
            if (track != null){
                em.remove(track);
            }
            em.getTransaction().commit();
        }
    }

    public TrackDTO update(Long id, TrackDTO TrackDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Track c = em.find(Track.class,id);
            c.setTitle(TrackDTO.getTitle());

            Track newTrack = em.merge(c);
            em.getTransaction().commit();
            return new TrackDTO(newTrack);
        }
    }

}
