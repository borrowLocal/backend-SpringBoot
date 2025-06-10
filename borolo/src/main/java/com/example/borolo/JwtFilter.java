package com.example.borolo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import com.example.borolo.domain.User;
import com.example.borolo.repository.UserDao;
import com.example.borolo.JwtUtil;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDao userDao;

    public JwtFilter(JwtUtil jwtUtil, UserDao userDao) {
        this.jwtUtil = jwtUtil;
        this.userDao = userDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);
        String uri = request.getRequestURI();
        System.out.println("[JwtFilter] 요청 URI: " + uri);
        System.out.println("[JwtFilter] 추출된 토큰: " + token);

        if (token != null && jwtUtil.validateToken(token)) {
            String userId = jwtUtil.getUserId(token);
            User user = userDao.findById(Integer.parseInt(userId));

            if (user != null && !user.getIs_deleted()) {
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(user, null, null);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}