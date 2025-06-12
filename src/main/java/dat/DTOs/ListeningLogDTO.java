package dat.DTOs;

import java.time.LocalDateTime;
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
public class ListeningLogDTO {
    private Long id;
    private Long userId;
    private Long trackId;
    private LocalDateTime listenedAt;
}
