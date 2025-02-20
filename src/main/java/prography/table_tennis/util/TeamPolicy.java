package prography.table_tennis.util;

import org.springframework.stereotype.Component;
import prography.table_tennis.domain.Room;
import prography.table_tennis.domain.RoomType;
import prography.table_tennis.domain.Team;
import prography.table_tennis.domain.UserRoom;
import prography.table_tennis.exception.DomainException;

import java.util.List;

@Component
public class TeamPolicy {

    public Team createAutoTeam(Room room) {

        int maxSize = (room.getRoomType() == RoomType.SINGLE) ? 1 : 2;

        List<UserRoom> userRooms = room.getUserRooms();

        // 방이 꽉 차있으면
        if ((maxSize * 2) <= userRooms.size()) {
            throw new DomainException();
        }

        long redCount = userRooms.stream().filter(ur -> ur.getTeam() == Team.RED).count();
        long blueCount = userRooms.stream().filter(ur -> ur.getTeam() == Team.BLUE).count();

        // 처음 입장(방 생성) 시 RED 배정
        if (userRooms.isEmpty()) {
            return Team.RED;
        }

        // 입장 시 적은 팀 배정
        return (redCount > blueCount) ? Team.BLUE : Team.RED;

    }
}
