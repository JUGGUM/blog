//package me.user.config;
//
//import lombok.RequiredArgsConstructor;
//import me.user.service.UserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@RequiredArgsConstructor
//@Configuration
//public class WebSecurityConfig {
//    private final UserDetailService userService;
//
//    //스프링 시큐리티 기능 비활성화(인증, 인가 서비스를 모든곳에 적용하지않는다.)
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**"); // 정적 리소스(이미지 html파일)
//    }
//
//    // 특정 HTTP 요청에 대한 웹 기반 보안 구성 , 인증/인가 및 로그인 로그아웃 관련 설정을 할수 있습니다.
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests() // 특정 경로에 대한 액세스 설정을 합니다.
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                .anyRequest() // 위에 설정한 url 이외의 요청에 대해서 설정
//                .authenticated() // 별도의 인가는 필요하지 x 인증이 접근
//                .and()
//                .formLogin() // 폼 기반 로그인 설정
//                .loginPage("/login") // 로그인 페이지의 경로를 설정합니다.
//                .defaultSuccessUrl("/articles")  // 로그인이 완료되었을때 이동할 경로를 설정
//                .and()
//                .logout() // 로그아웃 설정
//                .logoutSuccessUrl("/login") // 로그아웃이 완료되었을때 이동할 경로 설정
//                .invalidateHttpSession(true) // 로그아웃 이후에 세션을 전체 삭제할지 여부
//                .and()
//                .csrf().disable() // csrf 비활성화 (공격 방지를 위해 하는게 좋지만 실습을 위해 비활성화)
//                .build();
//    }
//
//    // 인증관리자관련설정
//    // 사용자 정보를 가져올 서비스를 재정의하거나, 인증방법, LDAP, JDBC 기반의 인증등을 설정할때사용
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http,
//                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
//                                                       UserDetailService userDetailService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userService) // 사용자 정보 서비스 설정
//                .passwordEncoder(bCryptPasswordEncoder)
//                // 비밀번호를 암호화하기 위한 인코더를 설정합니다.
//                .and()
//                .build();
//    }
//
//    // 패스워드 인코더로 사용할 빈 등록
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
