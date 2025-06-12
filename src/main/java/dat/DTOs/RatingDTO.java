package dat.DTOs;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private Long id;
    private Long userId;
    private Long albumId;
    private Integer rating;
    private String review;
    private LocalDateTime timestamp;
}