package prography.table_tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prography.table_tennis.domain.UserRoom;

import java.util.Optional;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {

    boolean existsByUserId(int userId);

    @Modifying
    @Query("delete from UserRoom ur where ur.room.id = :room_id")
    void deleteAllByRoomId(@Param("room_id") int roomId);

    @Modifying
    @Query("delete from UserRoom ur where ur.user.id = :userId")
    void deleteByUserId(@Param("userId") int userId);

    @Query("select ur from UserRoom ur join fetch ur.room where ur.user.id = :userId")
    Optional<UserRoom> findWithRoomByUserId(@Param("userId") int userId);
}
