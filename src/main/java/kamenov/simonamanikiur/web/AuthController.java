package kamenov.simonamanikiur.web;


import jakarta.validation.Valid;
import kamenov.simonamanikiur.entity.UserEntity;
import kamenov.simonamanikiur.entity.dtos.AuthResponse;
import kamenov.simonamanikiur.entity.dtos.LoginDto;
import kamenov.simonamanikiur.entity.dtos.RegisterDto;
import kamenov.simonamanikiur.services.JwtService;
import kamenov.simonamanikiur.services.RecaptchaService;
import kamenov.simonamanikiur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private final JwtService jwtUtil;

    @Autowired
    private final RecaptchaService recaptchaService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtUtil,
                          RecaptchaService recaptchaService,
                          AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.recaptchaService = recaptchaService;
        this.authenticationManager = authenticationManager;
    }

    // DTO класове за заявките


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto request) {
        // Валидиране на reCAPTCHA
        boolean recaptchaValid = recaptchaService.validateRecaptcha(request.recaptchaToken);
        if (!recaptchaValid) {
            return ResponseEntity.badRequest().body("Invalid reCAPTCHA");
        }
        // Проверка дали потребителят съществува (може да се добави и проверка за email)
        if (userService.findByUsername(request.username) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        UserEntity newUser = new UserEntity(request.username,
                request.fullName, request.email,
                request.password);
        userService.registerUser(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.password)
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Incorrect username or password");
        }
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, request.getUsername()));
    }

    @PostMapping("/login-error")
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter
            .SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);


//        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:/api/auth/login";
//        if (userServiceModel == null) {
//            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel());
//            redirectAttributes.addFlashAttribute("isFound", false);
//            return "redirect:login";
//        }
    }

}