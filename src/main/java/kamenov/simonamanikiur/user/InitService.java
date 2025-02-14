package kamenov.simonamanikiur.user;

import jakarta.annotation.PostConstruct;
import kamenov.simonamanikiur.entity.UserEntity;
import kamenov.simonamanikiur.entity.UserRoleEntity;
import kamenov.simonamanikiur.entity.enums.UserRoleEnum;
import kamenov.simonamanikiur.repos.UserRepository;
import kamenov.simonamanikiur.repos.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;


    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("pako") String defaultPassword) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;


        this.defaultPassword = defaultPassword;
    }


    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {

            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            var userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);


            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();

            initNormalUser();
        }
    }

    private void initAdmin() {
        var adminRole = userRoleRepository.
                findUserRoleEntityByRole(UserRoleEnum.ADMIN).orElseThrow();
        var adminUser = new UserEntity().
                setEmail("admin@example.com").
                setFullName("Admin").
                setUsername("admin").
                setPassword(passwordEncoder.encode(defaultPassword)).
                setRoles(List.of(adminRole));

        userRepository.save(adminUser);
    }


    private void initNormalUser() {
        var userRole = userRoleRepository.
                findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow();
        var normalUser = new UserEntity().
                setEmail("user@example.com").
                setFullName("User").
                setUsername("user").
                setRoles(List.of(userRole)).
                setPassword(passwordEncoder.encode(defaultPassword));

        userRepository.save(normalUser);
    }
}