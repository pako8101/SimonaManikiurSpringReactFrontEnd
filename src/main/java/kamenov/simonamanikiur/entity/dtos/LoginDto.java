package kamenov.simonamanikiur.entity.dtos;

import kamenov.simonamanikiur.entity.UserEntity;

public class LoginDto {
    public UserEntity username;
    public String password;

    public LoginDto() {
    }

    public UserEntity getUsername() {
        return username;
    }

    public LoginDto setUsername(UserEntity username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
