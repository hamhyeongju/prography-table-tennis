package prography.table_tennis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.table_tennis.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private int id;
    private int fakerId;
    private String name;
    private String email;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fakerId = user.getFakerId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}

