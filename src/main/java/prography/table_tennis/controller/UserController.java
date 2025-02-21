package prography.table_tennis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prography.table_tennis.dto.response.GetUsersResponse;
import prography.table_tennis.service.UserService;
import prography.table_tennis.util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ApiResponse<GetUsersResponse> getUsers(
            @RequestParam("size") int size,
            @RequestParam("page") int page
    ) {

        GetUsersResponse response = userService.getUsers(PageRequest.of(page, size));

        return new ApiResponse<>(response);
    }
}
