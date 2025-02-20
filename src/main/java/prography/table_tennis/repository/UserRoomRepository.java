package prography.table_tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prography.table_tennis.domain.UserRoom;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {

    boolean existsByUserId(int userId);
}
