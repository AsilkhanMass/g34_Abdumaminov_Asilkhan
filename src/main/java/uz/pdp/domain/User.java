package uz.pdp.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data

public class User {
    static private Integer sequence = 0;
    {
        sequence++;
    }
    private Integer id = sequence;
    private String name;
    private String email;
    private String password;
    @Builder.Default private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

}
