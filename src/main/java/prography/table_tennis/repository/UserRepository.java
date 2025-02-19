package prography.table_tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import prography.table_tennis.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("delete from User")
    void deleteAll();
}
