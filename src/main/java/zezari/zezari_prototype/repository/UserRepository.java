package zezari.zezari_prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zezari.zezari_prototype.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // username으로 사용자 정보를 가져옴
}
