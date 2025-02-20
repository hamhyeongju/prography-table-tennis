package prography.table_tennis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prography.table_tennis.dto.CreateRoomRequest;
import prography.table_tennis.service.RoomService;
import prography.table_tennis.util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/room")
    public ApiResponse<Object> createRoom(@RequestBody CreateRoomRequest request) {

        roomService.createRoom(request);

        return new ApiResponse<>();
    }
}
