package zezari.zezari_prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zezari.zezari_prototype.domain.Page;

public interface PageRepository extends JpaRepository<Page, Long> {
}
