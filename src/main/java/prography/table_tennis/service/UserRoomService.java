package prography.table_tennis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prography.table_tennis.domain.*;
import prography.table_tennis.exception.DomainException;
import prography.table_tennis.repository.RoomRepository;
import prography.table_tennis.repository.UserRepository;
import prography.table_tennis.repository.UserRoomRepository;
import prography.table_tennis.util.TeamPolicy;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final TeamPolicy teamPolicy;

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

    public void out(int userId, int roomId) {

        if (!userRoomRepository.existsByUserId(userId)) {
            throw new DomainException();
        }

        Room findRoom = roomRepository.findById(roomId)
                .orElseThrow(DomainException::new);

        // 방 상태 체크
        if (!findRoom.getStatus().equals(RoomStatus.WAIT)) {
            throw new DomainException();
        }

        // host가 out 요청
        if (userId == findRoom.getHost().getId()) {
            userRoomRepository.deleteAllByRoomId(roomId);

            findRoom.finish();
        }

        // 일반 user가 요청
        userRoomRepository.deleteByUserId(userId);

    }

    public void changeTeam(int userId, int roomId) {
        Room findRoom = roomRepository.findWithUserByRoomId(roomId)
                .orElseThrow(DomainException::new);

        // room 상태
        if (!findRoom.getStatus().equals(RoomStatus.WAIT)) {
            throw new DomainException();
        }

        UserRoom findUserRoom = userRoomRepository.findWithRoomByUserId(userId)
                .orElseThrow(DomainException::new);

        Team changeTeam = teamPolicy.changeTeam(findRoom, findUserRoom);
        findUserRoom.changeTeam(changeTeam);
    }
}
