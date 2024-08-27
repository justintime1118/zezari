package zezari.zezari_prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zezari.zezari_prototype.dto.UpdateUserRequest;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password") // 암호화한 형태로 저장
    private String password;

    @Column(name = "phone_number", nullable = true, unique = true)
    private String phoneNumber;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Builder
    public User(String username, String password, String phoneNumber, String auth) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void update(UpdateUserRequest request) {
        this.password = request.getPassword();
        this.phoneNumber = request.getPhoneNumber();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // true -> 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // true -> 잠금되지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // true -> 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        return true; // true -> 사용 가능
    }
}
