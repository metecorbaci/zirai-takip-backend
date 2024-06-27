package com.fileservice.util;
import com.fileservice.service.CustomAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomAuthService authService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomAuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Http request'ten token al
        String token = getTokenFromRequest(request);

        // Token doğrulama
        if(StringUtils.hasText(token) && jwtUtil.validateToken(token)){

            // Token içerisinden mail al
            String email = jwtUtil.getMail(token);

            // Mail'e göre kullanıcı bilgilerini al
            // NOT : loadUserByUsername metodu CustomAuthService sınıfında implemente edilmiştir ve bu metot kullanıcı bilgilerini döndürmektedir
            // User'a ait unique değer nizim projemizde email olduğu için email üzerinden kullanıcı bilgileri alınmaktadır.
            UserDetails userDetails = authService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}