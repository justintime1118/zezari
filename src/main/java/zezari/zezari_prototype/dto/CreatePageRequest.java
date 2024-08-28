package zezari.zezari_prototype.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zezari.zezari_prototype.domain.Page;
import zezari.zezari_prototype.domain.User;
import zezari.zezari_prototype.enums.Gender;

@Getter @Setter
@NoArgsConstructor
public class CreatePageRequest {
    private String name;
    private Gender gender;
    private Integer age;
    private String contact;
    private String message;

    @Builder
    public CreatePageRequest(String name, Gender gender, Integer age, String contact, String message) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.message = message;
    }

    public Page toEntity(User user) {
        return Page.builder()
                .user(user)
                .name(this.name)
                .gender(this.gender)
                .age(this.age)
                .contact(this.contact)
                .message(this.message)
                .build();
    }
}
