package prography.table_tennis.service;

import dto.InitializationRequest;
import dto.UserBulkInsertDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.table_tennis.client.FakerApiClient;
import prography.table_tennis.client.dto.FakeUser;
import prography.table_tennis.client.dto.FakeUserResponse;
import prography.table_tennis.repository.RoomRepository;
import prography.table_tennis.repository.UserRepository;
import prography.table_tennis.repository.jdbcRepository.UserJdbcRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitializationService {

    private final UserRepository userRepository;
    private final UserJdbcRepository userJdbcRepository;
    private final RoomRepository roomRepository;
    private final FakerApiClient fakerApiClient;

    private static final String KO_KR = "ko_KR";

    @Transactional
    public void init(InitializationRequest request) {
        // 정보 삭제
        userRepository.deleteAll();
        roomRepository.deleteAll();

        // 외부 api 요청
        FakeUserResponse fakeUserResponse = fakerApiClient.getUsers(request.getSeed(), request.getQuantity(), KO_KR);
        List<FakeUser> fakeUsers = fakeUserResponse.getData();

        // fake 데이터 매핑
        List<UserBulkInsertDto> userDtos = fakeUsers.stream().map(fakeUser -> {
                    if (fakeUser.getId() < 31) {
                        return UserBulkInsertDto.createActiveUserDto(fakeUser.getId(), fakeUser.getUsername(), fakeUser.getEmail());
                    }
                    if (fakeUser.getId() < 61) {
                        return UserBulkInsertDto.createWaitUserDto(fakeUser.getId(), fakeUser.getUsername(), fakeUser.getEmail());
                    }
                    return UserBulkInsertDto.createNonActiveUserDto(fakeUser.getId(), fakeUser.getUsername(), fakeUser.getEmail());
                }
        ).toList();

        // 데이터 저장
        userJdbcRepository.bulkInsertUser(userDtos);
    }
}
