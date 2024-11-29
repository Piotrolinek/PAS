package pl.lodz.p.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDTO {
    private UUID clientId;
    private UUID vmId;
    private LocalDateTime startTime;
    @Size(min = 4, max = 20)
    private String test;//TODO REMOVE LATER
}
