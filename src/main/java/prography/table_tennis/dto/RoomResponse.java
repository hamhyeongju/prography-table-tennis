package prography.table_tennis.dto;

import lombok.Getter;
import prography.table_tennis.domain.Room;
import prography.table_tennis.domain.RoomStatus;
import prography.table_tennis.domain.RoomType;

@Getter
public class RoomResponse {

    private int id;
    private String title;
    private int hostId;
    private RoomType roomType;
    private RoomStatus status;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.title = room.getTitle();
        this.hostId = room.getHost();
        this.roomType = room.getRoomType();
        this.status = room.getStatus();
    }
}
