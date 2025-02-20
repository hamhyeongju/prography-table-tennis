package prography.table_tennis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import prography.table_tennis.dto.*;
import prography.table_tennis.service.RoomService;
import prography.table_tennis.util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/room")
    public ApiResponse<GetRoomsResponse> getRoom(
            @RequestParam("size") int size,
            @RequestParam("page") int page
    ) {

        GetRoomsResponse
                response = roomService.getRooms(PageRequest.of(page, size));

        return new ApiResponse<>(response);
    }

    @GetMapping("/room/{roomId}")
    public ApiResponse<GetRoomResponse> getRoom(
            @PathVariable("roomId") int roomId
    ) {

        GetRoomResponse
                response = roomService.getRoom(roomId);

        return new ApiResponse<>(response);
    }

    @PostMapping("/room")
    public ApiResponse<Object> createRoom(@RequestBody CreateRoomRequest request) {

        roomService.createRoom(request);

        return new ApiResponse<>();
    }

    @PostMapping("/room/attention/{roomId}")
    public ApiResponse<Object> attention(
            @RequestBody PostAttentionRequest request,
            @PathVariable("roomId") int roomId
            ) {

        roomService.attention(request.getUserId(), roomId);

        return new ApiResponse<>();
    }
}
