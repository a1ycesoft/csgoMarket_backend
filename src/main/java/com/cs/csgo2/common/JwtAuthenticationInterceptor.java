package com.cs.csgo2.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cs.csgo2.common.JwtUtils;
import com.cs.csgo2.entity.Users;
import com.cs.csgo2.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
@Slf4j
public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.debug(request.getServletPath());
        // 跨域的预检请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS)) {
            return true;
        }

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        PrintWriter writer = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            String token = request.getHeader("Authorization");
            // 执行认证
            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                writer = response.getWriter();
                writer.append("无token，请重新登录");
                return false;
            }

            // 获取 token 中的 userId
            String userId = JwtUtils.getAudience(token);
            log.info("token中userid为："+userId);
            QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",userId);
            Users user = usersService.getOne(queryWrapper);
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                writer = response.getWriter();
                writer.append("用户不存在，请重新登录");
                return false;
            }

            // 验证 token
            boolean b = JwtUtils.verifyToken(token, user.getPassword());
            if (b) {
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                writer = response.getWriter();
                writer.append("token无效，请重新登录");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }
}

