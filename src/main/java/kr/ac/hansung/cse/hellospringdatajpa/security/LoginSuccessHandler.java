package kr.ac.hansung.cse.hellospringdatajpa.security;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // FlashMap을 이용해 한 번만 표시될 메시지 저장
        FlashMap flashMap = new FlashMap();
        flashMap.put("successMessage", "환영합니다, " + authentication.getName() + "님!");
        FlashMapManager flashMapManager = new SessionFlashMapManager();
        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        // (1) 리다이렉트할 기본 URL을 /products 로
        setDefaultTargetUrl("/products");
        setAlwaysUseDefaultTargetUrl(true);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
