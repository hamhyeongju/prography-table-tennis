package prography.table_tennis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.table_tennis.domain.*;
import prography.table_tennis.dto.*;
import prography.table_tennis.exception.DomainException;
import prography.table_tennis.repository.RoomRepository;
import prography.table_tennis.repository.UserRepository;
import prography.table_tennis.repository.UserRoomRepository;
import prography.table_tennis.util.TeamPolicy;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserRoomRepository userRoomRepository;

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
    public void attention(int userId, int roomId) {

        // 유저가 이미 참가한 방이 존재
        if (userRoomRepository.existsByUserId(userId)) {
            throw new DomainException();
        }

        Room findRoom = roomRepository.findById(roomId)
                .orElseThrow(DomainException::new);

        // room wait 상태
        if (!findRoom.getStatus().equals(RoomStatus.WAIT)) {
            throw new DomainException();
        }

        User findUser = userRepository.findById(userId)
                .orElseThrow(DomainException::new);

        // user active 상태
        if (!findUser.getStatus().equals(UserStatus.ACTIVE)) {
            throw new DomainException();
        }

        Team autoTeam = teamPolicy.createAutoTeam(findRoom);
        UserRoom userRoom = UserRoom.of(findUser, findRoom, autoTeam);
        userRoomRepository.save(userRoom);
    }
}
