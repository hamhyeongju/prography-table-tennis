package prography.table_tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prography.table_tennis.domain.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Modifying
    @Query("delete from Room")
    void deleteAll();

    @Query("select r from Room r join fetch r.host where r.id = :roomId")
    Optional<Room> findWithUserByRoomId(@Param("roomId") int roomId);
}
