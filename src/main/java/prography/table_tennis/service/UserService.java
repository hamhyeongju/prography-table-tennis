package prography.table_tennis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prography.table_tennis.domain.User;
import prography.table_tennis.dto.response.GetUsersResponse;
import prography.table_tennis.dto.response.UserResponse;
import prography.table_tennis.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public GetUsersResponse getUsers(Pageable pageable) {

        Page<User> findUsers = userRepository.findAll(pageable);

        List<UserResponse> userResponses =
                findUsers.getContent().
                stream().map(UserResponse::new).toList();

        return new GetUsersResponse(
                (int) findUsers.getTotalElements(),
                findUsers.getTotalPages(),
                userResponses);
    }
}
