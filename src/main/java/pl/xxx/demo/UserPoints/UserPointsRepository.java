package pl.xxx.demo.UserPoints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {


    @Query("SELECT SUM(up.points) FROM UserPoints up WHERE up.user.id = :userId")
    Integer sumPointsByUserId(@Param("userId") Long userId);

}


