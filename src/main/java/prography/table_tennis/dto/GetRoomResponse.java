package prography.table_tennis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import prography.table_tennis.domain.Room;
import prography.table_tennis.domain.RoomStatus;
import prography.table_tennis.domain.RoomType;

import java.time.LocalDateTime;

@Getter
public class GetRoomResponse {

    private int id;
    private String title;
    private int hostId; // userId
    private RoomType roomType; // SINGLE, DOUBLE
    private RoomStatus status; // WAIT, PROGRESS, FINISH
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public GetRoomResponse(Room room) {
        this.id = room.getId();
        this.title = room.getTitle();
        this.hostId = room.getHost();
        this.roomType = room.getRoomType();
        this.status = room.getStatus();
        this.createdAt = room.getCreatedAt();
        this.updatedAt = room.getUpdatedAt();
    }
}
