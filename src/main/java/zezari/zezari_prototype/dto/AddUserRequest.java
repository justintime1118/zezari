package zezari.zezari_prototype.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String password;
    private String phoneNumber;
}
