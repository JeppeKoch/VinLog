package dat.DTOs;
import dat.entities.Artist;
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
public class ArtistDTO {
    private Long id;
    private String name;
    private String country;

    public ArtistDTO(Artist artist){
        this.id = artist.getId();
        this.name = artist.getName();
        this.country = artist.getCountry();
    }
}