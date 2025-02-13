package kamenov.simonamanikiur.services.impl;

import kamenov.simonamanikiur.entity.UserEntity;
import kamenov.simonamanikiur.repos.UserRepository;
import kamenov.simonamanikiur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
@Override
    public UserEntity registerUser(UserEntity user) {
        // Криптиране на паролата
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
@Override
    public UserEntity findByUsername(String username) {
        return userRepository.findUserEntityByUsername(username).orElse(null);
    }
}
