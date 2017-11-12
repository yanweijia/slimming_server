package cn.yanweijia.slimming.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录验证拦截器
 *
 * @author weijia
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getSession().getAttribute("id") == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "没有登录或会话已过期,请登录");
            map.put("url", "/api/guest/login");
            httpServletResponse.getWriter().append(objectMapper.writeValueAsString(map));
            return false;
        }
        logger.debug("用户已登录");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
