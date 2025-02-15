package kamenov.simonamanikiur.config;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import kamenov.simonamanikiur.entity.UserRoleEntity;
import kamenov.simonamanikiur.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class AppConfig {
    @Value("${email_username}") private String email;
    @Value("${password_email}") private String password;
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
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(email);
        mailSender.setPassword(password);


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
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
