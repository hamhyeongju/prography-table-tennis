package prography.table_tennis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prography.table_tennis.dto.GetUsersResponse;
import prography.table_tennis.util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ApiResponse<GetUsersResponse> getUsers(
            @RequestParam int size,
            @RequestParam int page
    ) {

        GetUsersResponse response = userService.getUsers(size, page);

        return new ApiResponse<>(response);
    }
}
