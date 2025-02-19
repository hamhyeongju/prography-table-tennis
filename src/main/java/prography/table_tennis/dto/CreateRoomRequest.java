package prography.table_tennis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequest {

    private int userId;
    private String roomType;
    private String title;
}
