package kamenov.simonamanikiur.services.impl;

import kamenov.simonamanikiur.services.RecaptchaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    @Value("${secret_key_recaptcha}")
    private String recaptchaSecret;

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
@Override
    public boolean validateRecaptcha(String recaptchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("secret", recaptchaSecret);
        params.put("response", recaptchaResponse);
        // Използваме POST заявка към API-то на Google
        RecaptchaResponse apiResponse = restTemplate.postForObject(
                RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}",
                null,
                RecaptchaResponse.class,
                params
        );
        return apiResponse != null && apiResponse.success;
    }

    // Клас за мапване на отговора от Google
    public static class RecaptchaResponse {
        public boolean success;
        public String challenge_ts;
        public String hostname;
        public String[] errorCodes;
    }

}
