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
public class TrackDTO {
    private Long id;
    private String title;
    private Integer duration;
    private Integer trackNumber;
    private Long albumId;
}