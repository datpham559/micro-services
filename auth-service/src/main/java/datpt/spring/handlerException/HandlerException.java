package datpt.spring.handlerException;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Service
public class HandlerException {
    private final Logger log = LoggerFactory.getLogger(HandlerException.class);
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(Exception e) {
        AuthenticationException authenticationException = (AuthenticationException) e;
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(Exception e) {
        UsernameNotFoundException authenticationException = (UsernameNotFoundException) e;
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        String messageErr = getMessageError(e);
        log.error(messageErr);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), status);
    }

    public String getMessageError(Exception e) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Map<String, String[]> map = new HashMap<>();
            map = request.getParameterMap();
            StringBuilder params = new StringBuilder("\nParams: ");
            String requestInfo = "\nRequest: ";
            requestInfo += request.getRequestURI();
            for (String key : map.keySet()) {
                params.append(key).append(": ").append(map.get(key) != null ? Arrays.asList(map.get(key)).get(0).toString() : "").append(", ");
            }
            return "\nException: " + (!Arrays.asList(e.getStackTrace()).isEmpty() ? (!Arrays.asList(e.getStackTrace()).isEmpty() ? Arrays.asList(e.getStackTrace()).get(0).toString() : "") + (Arrays.asList(e.getStackTrace()).size() > 1 ? Arrays.asList(e.getStackTrace()).get(1).toString() : "") + (Arrays.asList(e.getStackTrace()).size() > 2 ? Arrays.asList(e.getStackTrace()).get(2).toString() : "") + (Arrays.asList(e.getStackTrace()).size() > 3 ? Arrays.asList(e.getStackTrace()).get(3).toString() : "") : null) + "\nCause: " + e.getClass().getName() + requestInfo + params;
        } catch (Exception ex) {
            return "";
        }
    }
}
