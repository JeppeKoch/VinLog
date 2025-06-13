package dat.DTOs;
import dat.entities.Album;
import jakarta.persistence.*;
import lombok.*;
/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String coverUrl;
    private Long artistId;

    public AlbumDTO(Album album){
        this.id = album.getId();
        this.title = album.getTitle();
        this.releaseYear = album.getReleaseYear();
        this.coverUrl = album.getCoverUrl();
        if (album.getArtist() != null) {
            this.artistId = album.getArtist().getId();
        }

    }
}
