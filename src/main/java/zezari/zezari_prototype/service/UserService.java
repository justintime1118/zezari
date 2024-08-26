package zezari.zezari_prototype.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zezari.zezari_prototype.domain.User;
import zezari.zezari_prototype.dto.AddUserRequest;
import zezari.zezari_prototype.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .build()).getId();
    }
}
