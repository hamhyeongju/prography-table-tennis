package prography.table_tennis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.table_tennis.domain.Room;
import prography.table_tennis.domain.User;
import prography.table_tennis.domain.UserStatus;
import prography.table_tennis.dto.*;
import prography.table_tennis.exception.DomainException;
import prography.table_tennis.repository.RoomRepository;
import prography.table_tennis.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;


    public void createRoom(CreateRoomRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(DomainException::new);

        if (!user.getStatus().equals(UserStatus.ACTIVE)) {
            throw new DomainException();
        }

        if (!Objects.isNull(user.getRoom())) {
            throw new DomainException();
        }

        Room room = Room.of(request.getTitle(), request.getUserId(), request.getRoomType());
        roomRepository.save(room);
    }

    public GetRoomsResponse getRooms(PageRequest request) {

        System.out.println("11");
        Page<Room> findRooms = roomRepository.findAll(request);
        System.out.println("@2");
        List<RoomResponse> roomResponses =
                findRooms.getContent().
                        stream().map(RoomResponse
                                ::new).toList();
        System.out.println("33");
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
}
