package kamenov.simonamanikiur.services;

public interface RecaptchaService {
    boolean validateRecaptcha(String recaptchaResponse);
}
