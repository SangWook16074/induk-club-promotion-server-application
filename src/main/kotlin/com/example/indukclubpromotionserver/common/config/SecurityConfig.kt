package com.example.indukclubpromotionserver.common.config

import com.example.indukclubpromotionserver.common.authority.JwtAuthenticationFilter
import com.example.indukclubpromotionserver.common.authority.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Bean
    fun filterChain(http : HttpSecurity) : SecurityFilterChain {
        http
            .httpBasic { it.disable() } // 끄기
            .csrf { it.disable() } //끄기
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 사용을 위해서 세션을 끔
            }
            .authorizeHttpRequests {
                /**
                 * requestMatchers에 기입한 Url을 요청하는 사용자는
                 * 권한이 없는 사용자고
                 * api/member/ 이하의 모든 api는 MEMBER 권한이 있어야 함.
                 */
                it.requestMatchers("/api/member/signup", "/api/member/login").anonymous()
                    .requestMatchers("/api/member/info/**").hasRole("MEMBER")
                    .anyRequest().permitAll()
            }
            .addFilterBefore(
                /**
                 * 필터의 실행 순서 정의
                 * 앞의 필터가 통과하면
                 * 뒤의 필터는 실행하지 않음
                 */
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
}