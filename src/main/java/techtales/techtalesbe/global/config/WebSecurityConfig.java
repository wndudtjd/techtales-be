package techtales.techtalesbe.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 경로별 인가 작업
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/cmn/auth/login.do").permitAll()
//                        .requestMatchers("/cmn/authorPgrm/selectAuthPgrmList1.do").hasRole("ADMIN")
//                        .requestMatchers("/cmn/reissue").permitAll()
//                        .requestMatchers("/pgrm/**").permitAll()
//                        .anyRequest().authenticated()
//                );

                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().permitAll()
                );

        // 예외 처리 추가
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            // 인증되지 않은 사용자를 프론트엔드 로그인 페이지로 리다이렉트
//                            response.sendRedirect("http://localhost:8091/cmn_ui/bbs/login.do");
//                        })
//                );
//        httpSecurity
//                .authorizeHttpRequests((auth) -> auth
//                        .anyRequest().permitAll()
//                );

        //JWTFilter 등록
//        httpSecurity
//                .addFilterBefore(new JWTFilter(jwtUtil, tokenService), LoginFilter.class);
//        //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
//        httpSecurity
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, tokenService), UsernamePasswordAuthenticationFilter.class);

        // CORS 설정을 추가하여 CorsConfigurationSource에서 설정한 CORS를 따르게 합니다.
        httpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:3000"));
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    corsConfig.setMaxAge(3600L);
                    return corsConfig;
                }));

        //세션 설정
        httpSecurity
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }
}
