package zezari.zezari_prototype.domain;

import jakarta.persistence.*;
import lombok.*;
import zezari.zezari_prototype.enums.Gender;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pages")
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name; // 찾는 분 이름

    @Enumerated(EnumType.STRING)
    private Gender gender; // 찾는 분 성별

    private Integer age; // 찾는 분 나이

    private String contact; // 연락받을 전화번호

    private String message; // 보호자가 전하고픈 말

    @Builder
    public Page(User user, String name, Gender gender, Integer age, String contact, String message) {
        this.user = user;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.message = message;
    }
}
