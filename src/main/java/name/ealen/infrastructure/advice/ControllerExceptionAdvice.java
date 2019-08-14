package name.ealen.infrastructure.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import name.ealen.infrastructure.utils.IpUtils;
import name.ealen.infrastructure.utils.TimeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EalenXie on 2018/11/8 16:25.
 * 全局异常 及其自定义异常 返回处理
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {


    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity throwable(Throwable throwable, HttpServletRequest request) {
        Map<String, String> resultMap = getThrowable(throwable);
        if (request != null) {
            Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
            resultMap.put("Requester-Ip", IpUtils.getIpAddress(request));
            resultMap.put("Requester-Agent", request.getHeader("user-agent"));
            if (statusCode != null) {
                new ResponseEntity<>(JSON.toJSON(resultMap).toString(), HttpStatus.valueOf(statusCode));
            }
        }
        return new ResponseEntity<>(JSON.toJSON(resultMap).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity authenticationException(AuthenticationException serverError) {
        Map<String, String> resultMap = getThrowable(serverError);
        return new ResponseEntity<>(JSON.toJSON(resultMap).toString(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = HttpStatusCodeException.class)
    public ResponseEntity httpStatusCodeException(HttpStatusCodeException serverError) {
        Map<String, String> resultMap = getThrowable(serverError);
        HttpStatus status = serverError.getStatusCode();
        resultMap.put("responseBody", "" + serverError.getResponseBodyAsString());
        resultMap.put("statusCode", "" + status.toString());
        resultMap.put("statusText", "" + serverError.getStatusText());
        resultMap.put("statusReasonPhrase", "" + status.getReasonPhrase());
        return new ResponseEntity<>(JSON.toJSON(resultMap).toString(), status);
    }

    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ResponseEntity incorrectCredentialsException(IncorrectCredentialsException i) {
        Map<String, String> resultMap = getThrowable(i);
        resultMap.put("message", "用户名/密码错误,认证失败");
        return new ResponseEntity<>(JSON.toJSON(resultMap).toString(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity unauthorizedException(UnauthorizedException i) {
        Map<String, String> resultMap = getThrowable(i);
        resultMap.put("message", "用户无角色(权限)访问此接口");
        return new ResponseEntity<>(JSON.toJSON(resultMap).toString(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 公共异常信息
     */
    private Map<String, String> getThrowable(Throwable throwable) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("throwable", "" + throwable);
        resultMap.put("throwableTime", "" + TimeUtils.getCurrentDateTime());
        resultMap.put("message", "" + throwable.getMessage());
        resultMap.put("localizedMessage", "" + throwable.getLocalizedMessage());
        log.error("Exception : {}", JSON.toJSON(resultMap));
        log.error("printStackTrace", throwable);
        return resultMap;
    }
}

