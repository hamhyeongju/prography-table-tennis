package prography.table_tennis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import prography.table_tennis.domain.*;
import prography.table_tennis.dto.request.CreateRoomRequest;
import prography.table_tennis.dto.response.GetRoomResponse;
import prography.table_tennis.dto.response.GetRoomsResponse;
import prography.table_tennis.dto.response.RoomResponse;
import prography.table_tennis.exception.DomainException;
import prography.table_tennis.repository.RoomRepository;
import prography.table_tennis.repository.UserRepository;
import prography.table_tennis.repository.UserRoomRepository;
import prography.table_tennis.util.TeamPolicy;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserRoomRepository userRoomRepository;

    private final ThreadPoolTaskScheduler taskScheduler;
    private final TransactionTemplate transactionTemplate;

    private final TeamPolicy teamPolicy;

    @Transactional
    public void createRoom(CreateRoomRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(DomainException::new);

        if (!user.getStatus().equals(UserStatus.ACTIVE)) {
            throw new DomainException();
        }

        if (userRoomRepository.existsByUserId(user.getId())) {
            throw new DomainException();
        }

        Room room = Room.of(request.getTitle(), user, request.getRoomType());
        roomRepository.save(room);

        Team autoTeam = teamPolicy.createAutoTeam(room);
        UserRoom userRoom = UserRoom.of(user, room, autoTeam);
        userRoomRepository.save(userRoom);
    }

    public GetRoomsResponse getRooms(PageRequest request) {

        Page<Room> findRooms = roomRepository.findAll(request);

        List<RoomResponse> roomResponses =
                findRooms.getContent().
                        stream().map(RoomResponse
                                ::new).toList();

        return new GetRoomsResponse(
                (int) findRooms.getTotalElements(),
                findRooms.getTotalPages(),
                roomResponses);
    }

    public GetRoomResponse getRoom(int roomId) {
        Room findRoom = roomRepository.findById(roomId)
                .orElseThrow(DomainException::new);

        return new GetRoomResponse(findRoom);
    }

    @Transactional
    public void startGame(int userId, int roomId) {
        Room findRoom = roomRepository.findWithUserByRoomId(roomId)
                .orElseThrow(DomainException::new);

        // host만 실행 가능
        if (findRoom.getHost().getId() != userId) {
            throw new DomainException();
        }

        // 방 상태 체크
        if (!findRoom.getStatus().equals(RoomStatus.WAIT)) {
            throw new DomainException();
        }

        // 방 정원 체크
        int maxSize = (findRoom.getRoomType() == RoomType.SINGLE) ? 2 : 4;
        if (maxSize != findRoom.getUserRooms().size()) {
            throw new DomainException();
        }

        findRoom.start();
        taskScheduler.schedule(
                () -> stopGame(roomId),
                Instant.now().plusSeconds(10)
        );

    }

    @Async
    public void stopGame(int roomId) {

        transactionTemplate.execute(
                status -> {
                    Room room = roomRepository.findById(roomId)
                            .orElseThrow(DomainException::new);
                    room.finish();
                    userRoomRepository.deleteAllByRoomId(roomId);
                    return null; // 트랜잭션 종료
                }
        );
    }
}
