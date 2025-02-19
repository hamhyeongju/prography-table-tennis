package prography.table_tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import prography.table_tennis.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Modifying
    @Query("delete from Room")
    void deleteAll();

}
