package prography.table_tennis.dto;

import lombok.Getter;
import lombok.Setter;
import prography.table_tennis.domain.RoomType;

@Getter
@Setter
public class CreateRoomRequest {

    private int userId;
    private RoomType roomType;
    private String title;
}
