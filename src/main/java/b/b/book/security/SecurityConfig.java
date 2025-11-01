package b.b.book.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import b.b.book.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor //SecurityConfig အလုပ်လုပ်တာနဲ့userService ကို ConstructorကနေInjectionလုပ်ပေးလိမ့်မယ်
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private final UserService userService;

  @Bean
  public UserDetailsService userDetailsService() {
    return userService;
  }
  //daoAuthenticationProvider သုံးပြီးdaoProvider တစ်ခုဆောက် userService ကိုခေါ်သုံးပြီးအလုပ်လုပ်မယ်
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
    daoProvider.setUserDetailsService(userService);
    daoProvider.setPasswordEncoder(passwordEncoder());
    return daoProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(httpForm -> {
          httpForm.loginPage("/login").permitAll();
          httpForm.defaultSuccessUrl("/index");
        })
        .authorizeHttpRequests(registry -> {
          registry.requestMatchers("register","/css/**","/images/**","/ja/**").permitAll();
          registry.anyRequest().authenticated();
                 })
                 .build();
  } 
}