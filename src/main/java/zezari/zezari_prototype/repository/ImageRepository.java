package zezari.zezari_prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zezari.zezari_prototype.domain.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPageId(Long pageId);
}
