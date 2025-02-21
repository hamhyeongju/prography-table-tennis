package prography.table_tennis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import prography.table_tennis.dto.request.PostAttentionRequest;
import prography.table_tennis.dto.request.PostChangeTeamRequest;
import prography.table_tennis.dto.request.PostUserRoomOutRequest;
import prography.table_tennis.service.UserRoomService;
import prography.table_tennis.util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UserRoomController {

    private final UserRoomService userRoomService;

    @PostMapping("/room/attention/{roomId}")
    public ApiResponse<Object> attention(
            @RequestBody PostAttentionRequest request,
            @PathVariable("roomId") int roomId
    ) {

        userRoomService.attention(request.getUserId(), roomId);
        return new ApiResponse<>();
    }

    @PostMapping("/room/out/{roomId}")
    public ApiResponse<Object> attention(
            @RequestBody PostUserRoomOutRequest request,
            @PathVariable("roomId") int roomId
    ) {

        userRoomService.out(request.getUserId(), roomId);
        return new ApiResponse<>();
    }

    @PutMapping("/team/{roomId}")
    public ApiResponse<Object> changeTeam(
            @RequestBody PostChangeTeamRequest request,
            @PathVariable("roomId") int roomId
            ) {
        userRoomService.changeTeam(request.getUserId(), roomId);
        return new ApiResponse<>();
    }


}
