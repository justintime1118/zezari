package zezari.zezari_prototype.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zezari.zezari_prototype.domain.Image;
import zezari.zezari_prototype.domain.Page;
import zezari.zezari_prototype.domain.User;
import zezari.zezari_prototype.dto.CreatePageRequest;
import zezari.zezari_prototype.repository.ImageRepository;
import zezari.zezari_prototype.repository.PageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;

    private final ImageRepository imageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Page createPage(CreatePageRequest dto, User user, List<MultipartFile> imageFiles) throws IOException {
        Page savedPage = pageRepository.save(dto.toEntity(user));

        List<Image> images = imageFiles.stream()
                .filter(file -> !file.isEmpty())
                .map(file -> {
                    try {
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path filePath = Paths.get(uploadDir + fileName);
                        Files.write(filePath, file.getBytes());
                        return Image.builder()
                                .fileName(fileName)
                                .filePath(filePath.toString())
                                .page(savedPage)
                                .build();

                    } catch (IOException e) {
                        throw new RuntimeException("Failed to save image", e);
                    }
                })
                .toList();

        imageRepository.saveAll(images);

        return savedPage;
    }

    public Page findPageById(Long id) {
        return pageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Page not found"));
    }

    public List<Image> findImagesForPage(Long pageId) {
        return imageRepository.findByPageId(pageId);
    }

    public String getFullPath(String filename) {
        return uploadDir + filename;
    }
}
