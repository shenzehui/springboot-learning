package org.javabot.securitydemo.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author szh
 * @Date 2022/5/20 16:20
 * @PackageName:org.javabot.securitydemo.config
 * @ClassName: MyWebAuthenticationDetails
 * @Description: 增强WebAuthenticationDetails 类 ，增加验证码是否通过属性
 * @Version 1.0
 */
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {
    /*是否通过*/
    private boolean isPassed;

    public boolean isPassed() {
        return isPassed;
    }

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public MyWebAuthenticationDetails(HttpServletRequest request) {
        /*存用户请求ip sessionid*/
        super(request);
        String code = request.getParameter("code");
        String vf = (String) request.getSession().getAttribute("vf");
        if(vf != null && code != null && code.equals(vf)){
            isPassed = true;
        }
    }
}
