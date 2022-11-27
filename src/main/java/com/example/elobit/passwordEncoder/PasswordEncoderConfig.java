package com.example.elobit.passwordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder customPasswordEncoder() {

        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence Password) {

                return BCrypt.hashpw(Password.toString(), BCrypt.gensalt(4));
            }

            @Override
            public boolean matches(CharSequence Password, String encodedPassword) {

                return BCrypt.checkpw(Password.toString(), encodedPassword);
            }
        };
    }
}
