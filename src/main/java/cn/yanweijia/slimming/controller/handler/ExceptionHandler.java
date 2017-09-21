package cn.yanweijia.slimming.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler implements HandlerExceptionResolver {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 统一异常处理
     *
     * @param request
     * @param response
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        logger.debug("捕获到异常", e);
        if (request.getServletPath().startsWith("/api")) {
            ModelAndView model = new ModelAndView(new MappingJackson2JsonView());
            model.addObject("success", false);
            model.addObject("message", "服务器错误:" + e.getClass().getName());
            model.addObject("exceptionLocate", e.getStackTrace()[0].toString());
            model.addObject("path", request.getContextPath() + request.getServletPath());
            model.addObject("time", new java.util.Date());
            return model;
        } else {
            ModelAndView model = new ModelAndView("error");
            model.addObject("reason", e.getClass().getName());
            model.addObject("exceptionLocate", e.getStackTrace()[0].toString());
            return model;
        }
    }
}
