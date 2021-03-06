package my.edu.umk.pams.intake.security.integration.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.intake.security.integration.jwt.vo.LoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by shazin on 12/7/16.
 */
@Component
public class JwtAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        LoginResponse error = new LoginResponse();
        error.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        error.setCode(UUID.randomUUID().toString());
        error.setToken(exception.getMessage());
        response.getWriter().print(new ObjectMapper().writeValueAsString(error));
        response.getWriter().flush();
    }
}
