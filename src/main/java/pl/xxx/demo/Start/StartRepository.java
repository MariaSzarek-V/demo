package pl.xxx.demo.Start;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartRepository extends JpaRepository<Start, Long> {

}
