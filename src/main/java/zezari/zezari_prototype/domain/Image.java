package zezari.zezari_prototype.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "images")
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public Image(String fileName, String filePath, Page page) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.page = page;
    }
}
