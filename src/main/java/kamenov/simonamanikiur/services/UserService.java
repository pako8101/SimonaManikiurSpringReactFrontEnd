package kamenov.simonamanikiur.services;

import kamenov.simonamanikiur.entity.UserEntity;

public interface UserService {
    UserEntity registerUser(UserEntity user);

    UserEntity findByUsername(String username);
}
