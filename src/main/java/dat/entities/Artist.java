package dat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Purpose: Repræsenterer en kunstner i Vinlog-systemet.
 * Forfatter: Jeppe Koch
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // matches "id SERIAL PRIMARY KEY" i DB
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Album> albums = new HashSet<>();

    // Optional constructor for easier instantiation
    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void addAlbum(Album album) {
        albums.add(album);
        album.setArtist(this);
    }
}
