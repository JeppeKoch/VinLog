package dat.entities;
import dat.DTOs.AlbumDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "cover_url")
    private String coverUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Track> tracks = new HashSet<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings = new HashSet<>();


    public Album(AlbumDTO albumDTO) {
        this.id = albumDTO.getId();
        this.title = albumDTO.getTitle();
        this.releaseYear = albumDTO.getReleaseYear();
        this.coverUrl = albumDTO.getCoverUrl();
        if (albumDTO.getArtistId() != null) {
            this.artist = new Artist(albumDTO.getArtistId());
        }
    }
}
