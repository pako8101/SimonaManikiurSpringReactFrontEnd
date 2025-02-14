package kamenov.simonamanikiur.entity.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kamenov.simonamanikiur.entity.UserEntity;
import kamenov.simonamanikiur.validation.uniqueUsername.UniqueUsername;
import org.springframework.security.core.userdetails.User;

public class LoginDto {
    @NotBlank
    @Size(min = 3, max = 20)
    @UniqueUsername
    private UserEntity username;
    @NotBlank
    @Size(min = 8, max = 20)
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
