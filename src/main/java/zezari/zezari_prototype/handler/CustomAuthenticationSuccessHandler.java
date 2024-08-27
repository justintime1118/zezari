package zezari.zezari_prototype.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zezari.zezari_prototype.domain.User;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Authentication에서 UserDetails 가져오기
        User user = (User) authentication.getPrincipal();

        // 사용자 ID를 사용하여 리디렉션 경로 생성
        Long userId = user.getId();
        response.sendRedirect("/users/" + userId);
    }
}
