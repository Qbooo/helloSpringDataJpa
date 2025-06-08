package kr.ac.hansung.cse.hellospringdatajpa.security;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        // FlashMap을 이용해 한 번만 표시될 에러메시지 저장
        FlashMap flashMap = new FlashMap();
        flashMap.put("errorMessage", "로그인에 실패했습니다: " + exception.getMessage());
        FlashMapManager flashMapManager = new SessionFlashMapManager();
        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        // 실패 시 로그인 페이지로 다시 포워드
        setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
