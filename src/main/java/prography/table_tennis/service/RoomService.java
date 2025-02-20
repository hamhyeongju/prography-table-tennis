package prography.table_tennis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.table_tennis.domain.Room;
import prography.table_tennis.domain.User;
import prography.table_tennis.domain.UserStatus;
import prography.table_tennis.dto.CreateRoomRequest;
import prography.table_tennis.exception.DomainException;
import prography.table_tennis.repository.RoomRepository;
import prography.table_tennis.repository.UserRepository;

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
}
