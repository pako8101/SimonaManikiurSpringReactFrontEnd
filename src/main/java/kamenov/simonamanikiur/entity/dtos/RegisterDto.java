package kamenov.simonamanikiur.entity.dtos;

public class RegisterDto {
    public String username;
    public String password;
    public String email;
    public String recaptchaToken;

    public RegisterDto() {
    }

    public String getUsername() {
        return username;
    }

    public RegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public RegisterDto setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
        return this;
    }
}
