package dat.DTOs;
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
}