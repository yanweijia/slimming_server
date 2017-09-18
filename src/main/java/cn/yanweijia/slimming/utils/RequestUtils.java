package cn.yanweijia.slimming.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
    /**
     * 获取访问者IP地址<br/>
     * 参考:<a href="https://zhidao.baidu.com/question/1992988105768972587.html">百度知道:springmvc怎么获取用户ip</a>
     * <p>在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。</p>
     * <p>本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)。</p>
     * <p>如果还不存在则调用Request.getRemoteAddr()。</p>
     *
     * @param request request
     * @return 用户IP
     */
    public static String getIPAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
