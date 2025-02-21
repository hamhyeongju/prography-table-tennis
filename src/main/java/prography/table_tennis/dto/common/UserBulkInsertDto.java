package prography.table_tennis.dto.common;

import lombok.Getter;
import prography.table_tennis.domain.UserStatus;

import java.time.LocalDateTime;

@Getter
public class UserBulkInsertDto {

    private Integer fakerId;
    private String name;
    private String email;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserBulkInsertDto createActiveUserDto (Integer fakerId, String name, String email) {
        UserBulkInsertDto dto = createUserBulkInsert(fakerId, name, email);
        dto.status = UserStatus.ACTIVE.toString();
        return dto;
    }
    public static UserBulkInsertDto createWaitUserDto (Integer fakerId, String name, String email) {
        UserBulkInsertDto dto = createUserBulkInsert(fakerId, name, email);
        dto.status = UserStatus.WAIT.toString();
        return dto;
    }
    public static UserBulkInsertDto createNonActiveUserDto (Integer fakerId, String name, String email) {
        UserBulkInsertDto dto = createUserBulkInsert(fakerId, name, email);
        dto.status = UserStatus.NON_ACTIVE.toString();
        return dto;
    }


    private static UserBulkInsertDto createUserBulkInsert(Integer fakerId, String name, String email) {
        UserBulkInsertDto dto = new UserBulkInsertDto();
        dto.fakerId = fakerId;
        dto.name = name;
        dto.email = email;
        dto.createdAt = LocalDateTime.now();
        dto.updatedAt = LocalDateTime.now();
        return dto;
    }

}
