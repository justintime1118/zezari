package zezari.zezari_prototype.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zezari.zezari_prototype.domain.User;
import zezari.zezari_prototype.dto.AddUserRequest;
import zezari.zezari_prototype.dto.UpdateUserRequest;
import zezari.zezari_prototype.repository.UserRepository;

import java.util.Optional;

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

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
    }

    @Transactional //TODO @Transactional을 붙이지 않으면 웹 화면으로 post 요청 날린 뒤에 테스트할 때, 변경 결과가 반영되지 않는 이유는?
    public void updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.update(request);
    }

}
