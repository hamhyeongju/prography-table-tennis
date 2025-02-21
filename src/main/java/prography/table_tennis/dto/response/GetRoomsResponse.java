package prography.table_tennis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetRoomsResponse {

    private int totalElements;
    private int totalPages;
    private List<RoomResponse> roomList;

}
