package prography.table_tennis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prography.table_tennis.dto.request.PostAttentionRequest;
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

}
