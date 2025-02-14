package kamenov.simonamanikiur.config;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import kamenov.simonamanikiur.entity.UserRoleEntity;
import kamenov.simonamanikiur.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public Gson gson() {
        return new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                create();
    }
//    @Bean
//    public CommandLineRunner init(UserRepository userRepository,
//                                  PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Ако няма потребител с потребителско име "admin", създаваме администраторски потребител
//            if (!userRepository.findByUsername("admin").isPresent()) {
//                UserRoleEntity admin = new UserRoleEntity("admin", passwordEncoder.encode("adminpass"),
//                        "admin@example.com", "ROLE_ADMIN");
//                userRepository.save(admin);
//                System.out.println("Default admin user created.");
//            }
//        };
//    }
}
