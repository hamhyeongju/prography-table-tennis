package prography.table_tennis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUsersResponse {

    private int totalElements;
    private int totalPages;
    private List<UserResponse> userList;
}
